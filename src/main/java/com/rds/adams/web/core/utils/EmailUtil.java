/**
 * 
 */
package com.rds.adams.web.core.utils;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.rds.adams.web.core.utils.dto.EmailDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JeongHyunseung
 *
 */
@Slf4j
public class EmailUtil {
	
	private static final String CONNECTION_URL = "https://rds-prod-sns.korea.communication.azure.com/";
	private static final String ACCESS_KEY = "Em8Hm3Jut22EUbbdFX7ZYPKBzemFe2qwMIkxyFmxzmaE6lsP3424JQQJ99AHACULyCpVgJAtAAAAAZCSW5L6"; 
	private static final String CONNECTION_STRING = "endpoint="+CONNECTION_URL+";accesskey="+ACCESS_KEY;
    private static final String SENDER_ADDRESS = "DoNotReply@rnadatasystem.com";

    public static void sendEmail(EmailDTO emailDTO) throws Exception {

        EmailClient client = new EmailClientBuilder()
            .connectionString(CONNECTION_STRING)
            .buildClient();

        EmailMessage message = new EmailMessage()
            .setSenderAddress(SENDER_ADDRESS)
            .setToRecipients(emailDTO.getRecipientAddress())
            .setSubject(emailDTO.getSubject())
            .setBodyPlainText(emailDTO.getBodyPlainText())
            //.setBodyHtml("<html><h1>This is the html body of test email.</h1></html>")
            ;

        try {
        	
            client.beginSend(message, null);
            /*
            PollResponse<EmailSendResult> pollResponse = null;

            Duration timeElapsed = Duration.ofSeconds(0);

             while (pollResponse == null
                     || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                     || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS)
             {
                 pollResponse = poller.poll();
                 log.debug("Email send poller status: " + pollResponse.getStatus());

                 Thread.sleep(POLLER_WAIT_TIME.toMillis());
                 timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                 if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0) {
                     throw new RuntimeException("Polling timed out.");
                 }
                 
             }
             
             if (poller.getFinalResult().getStatus() == EmailSendStatus.SUCCEEDED) {
            	 log.debug("Successfully sent the email (operation id: %s)", poller.getFinalResult().getId());
             } else {
                 throw new RuntimeException(poller.getFinalResult().getError().getMessage());
             }
             */
        } catch (Exception exception) {
            throw exception;
        }
        
    }

}