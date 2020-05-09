package org.apache.flink.streaming.connectors.rabbitmq;

import org.apache.flink.api.java.typeutils.ResultTypeQueryable;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.Serializable;

/**
 * Interface for the set of methods required to parse an RMQ delivery.
 * @param <T> The output type of the {@link RMQSource}
 */
public interface RMQDeliveryParser<T> extends Serializable, ResultTypeQueryable<T> {
	/**
	 * This method takes all the RabbitMQ delivery information supplied by the client and returns an output matching
	 * the {@link RMQSource}.
	 * @param envelope
	 * @param properties
	 * @param body
	 * @return an output T matching the output of the RMQSource
	 * @throws IOException
	 */
	public T parse(Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException;

	/**
	 * A method that extracts a unique correlation id from the RabbitMQ delivery information. This ID is used for
	 * deduplicating the messages in the RMQSource.
	 * @param envelope
	 * @param properties
	 * @param body
	 * @return
	 */
	public String getCorrelationID(Envelope envelope, AMQP.BasicProperties properties, byte[] body);
}
