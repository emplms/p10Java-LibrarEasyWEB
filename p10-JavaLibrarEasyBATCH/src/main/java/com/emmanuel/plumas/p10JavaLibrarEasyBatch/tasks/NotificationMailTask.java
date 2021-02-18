package com.emmanuel.plumas.p10JavaLibrarEasyBatch.tasks;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.BookEntityAvailable;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.Models.ReservationEntity;
import com.emmanuel.plumas.p10JavaLibrarEasyBatch.proxies.ApiProxy;

@Service
@Qualifier("notificationMailTask")
public class NotificationMailTask {

	@Autowired
	@Qualifier("ApiProxy")
	private ApiProxy apiProxy;
	
	public void execute() throws MessagingException {
		
		//Récupérer la liste des livres
		List<BookEntityAvailable> bookEntities=apiProxy.getAllBooks();
		
		//Pour chaque livre
		for(BookEntityAvailable bookEntityAvailable:bookEntities) {
			
			List<ReservationEntity> reservationEntities=apiProxy.getReservationByBookEntity(bookEntityAvailable.getBookId());
			boolean reservationNotificationForTheBookIsSended=false;
			int numberOfReservationForTheBook=reservationEntities.size();
			int numberOfCopyAvailable=bookEntityAvailable.getAvailableCopyNumber();
			
			/* Le traitement ne se fait que si 
			 * -> Une réservation n'a pas été envoyée pour le libre en question
			 * -> Il existe au moins une réservation pour le livre en question
			 * -> Il reste des exemplaires à disposition
			 * */
			
			while(!reservationNotificationForTheBookIsSended && numberOfReservationForTheBook!=0 && numberOfCopyAvailable!=0) {
				ReservationEntity prioritaryReservationEntity=definePrioritaryReservationByBookId(reservationEntities);
				if(prioritaryReservationEntity.getNotificationDate()==null) {
					manageReservationNotification(prioritaryReservationEntity);
					reservationNotificationForTheBookIsSended=true;
					numberOfCopyAvailable--;
				}else{
					Calendar calendar=Calendar.getInstance();
					calendar.add(Calendar.DATE, -2); //48h00 avant
					if(prioritaryReservationEntity.getNotificationDate().before(calendar.getTime())) {
						apiProxy.deleteReservation(prioritaryReservationEntity.getReservationId());
						numberOfReservationForTheBook--;
						reservationEntities.remove(prioritaryReservationEntity);
					}else {
						reservationNotificationForTheBookIsSended=true;
					}
				}
			}
		}
	}
	
	private ReservationEntity definePrioritaryReservationByBookId(List<ReservationEntity> reservationEntities) {
		ReservationEntity prioritaryReservationEntity=reservationEntities.get(0);
		int positionOnReservationList=reservationEntities.get(0).getPosition();
		for(ReservationEntity reservationEntity:reservationEntities) {
			if(reservationEntity.getPosition()<positionOnReservationList) {
				prioritaryReservationEntity=reservationEntity;
			}
		}
		return prioritaryReservationEntity;
	}
	
	private void manageReservationNotification(ReservationEntity reservationEntity) throws MessagingException {
		sendNotificationMailToUserEntity(reservationEntity);
		Date date = new Date();
		reservationEntity.setNotificationDate(date);
		apiProxy.upDateReservation(reservationEntity);
		System.out.println("mise à jour de la date notification de réservation");
	}
	
	private void sendNotificationMailToUserEntity(ReservationEntity reservationEntity) throws MessagingException {
		
		//Paramètres d'initialisation
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.host", "smtp.free.fr");
		// port 465 si reseau autre que free et port 25 si reseau free.
		prop.put("mail.smtp.port", "465");
		//Nécessaire si connection autre que free
		prop.put("mail.smtp.ssl.enable", "true");
		
		//Création de la session de connection avec le serveur de mail et Authentification
		Session session = Session.getInstance(prop, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("epika@free.fr", "PETIL33");
		    		}
				});
		
		
		//Création du message
		Message message = new MimeMessage(session);
		
		//Destinataire
		message.setFrom(new InternetAddress("epika@free.fr"));
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(reservationEntity.getUserEntity().getUserEmail()));
		
		//Sujet
		message.setSubject("Votre réservation");
		 
		//Corps du message
		String newLine=System.lineSeparator();
		String line1 = "Bonjour ";
		String line2= "Vous avez réservé à la bibliothèque l'ouvrage suivant : "+newLine;
		String line3= newLine+". L'ouvrage vous attend à la bibliothèque. Il vous est réservé pendant 48h00."+newLine;
		String line4="Au plaisir de vous revoir dans nos rayons."+newLine;
		String line5="Toute l'équipe de la bibliothèque";
		
		//Construction du message
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(line1
				+reservationEntity.getUserEntity().getUserFirstName()+" "
				+reservationEntity.getUserEntity().getUserLastName()+". "
				+line2
				+reservationEntity.getBookEntity().getBookTitle()+". "
				+line3+line4+line5, "text/html");
		 
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		 
		message.setContent(multipart);	
		
		try {        
	         Transport.send(message);
	         System.out.println("Message de notification suite à réservation envoyé avec succès....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			} 
		
		
	}
}	