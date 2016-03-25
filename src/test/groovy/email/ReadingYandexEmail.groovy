package email

import com.terminal.pages.StaticData

import javax.mail.*
import javax.mail.search.FlagTerm

public class ReadingYandexEmail {

    public static void main(newMessages,loginUser,passwordUser) throws Exception {
        //def newMessages = []
        Properties properties = new Properties()
        properties.put("mail.store.protocol", "imap")
        properties.put("mail.imap.host", "imap.yandex.ru")
        properties.put("mail.imap.port", 993)
        properties.put("mail.imap.ssl.protocols", "SSL")
        properties.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        properties.put("mail.imap.socketFactory.port", 993)
        properties.put("mail.imap.socketFactory.fallback", "false")
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(loginUser, passwordUser)
            }
        })
        Store store = session.getStore("imaps")
        store.connect("imap.yandex.ru", 993, loginUser, passwordUser)

        Folder inbox = store.getFolder("INBOX")
        inbox.open(Folder.READ_WRITE)

        def unseenMessages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false))
        unseenMessages.each { msg ->
            //noinspection GroovyAssignabilityCheck
            if (msg.getFrom().address[0] == StaticData.getSupportMail()) {
                def newMessage = readMessage(msg)
                newMessages.add(newMessage)
                msg.setFlag(Flags.Flag.SEEN, true)
            }
        }
        if(inbox) {
            inbox.close(true)
        }
        store.close()
    }

    static def readMessage(Message message) {
        def newMessage = [:]
        newMessage.subject = message.getSubject()
        newMessage.to = message.getRecipients(Message.RecipientType.TO).address[0]
        newMessage.from = message.getFrom().address[0]
        newMessage.content = message.getInputStream().getText().trim()
        newMessage.sentDate = message.getSentDate()
        //newMessage.headers = message.getAllHeaders()
        return newMessage
    }
}