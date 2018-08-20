package com.github.nielsonrocha.eventlistener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.swing.SwingUtilities;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MessageListener {

	private final static String QUEUE_NAME = "hello-queue";

	public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		// inciando conexão com RabbitMQ * em produção deve usar autenticação
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// setando o canal da fila
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Aguardando mensagens");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Recebida '" + message + "'");
				// aqui vc pode até abrir um frame, no caso vai abrir somente um dialog o ideal é criar uma nova thread para cada frame
				SwingUtilities.invokeLater(new JFrameTest());
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
