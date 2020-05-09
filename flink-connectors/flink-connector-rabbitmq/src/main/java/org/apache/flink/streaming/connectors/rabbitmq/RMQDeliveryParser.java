/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
