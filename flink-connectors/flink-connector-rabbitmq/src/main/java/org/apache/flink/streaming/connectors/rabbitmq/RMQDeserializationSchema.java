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

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.Serializable;

/**
 * Interface for the set of methods required to parse an RMQ delivery.
 * @param <T> The output type of the {@link RMQSource}
 */
public interface RMQDeserializationSchema<T> extends Serializable, ResultTypeQueryable<T> {
	/**
	 * This method takes all the RabbitMQ delivery information supplied by the client extract the data and pass it to the
	 * collector.
	 * NOTICE: The implementation of this method MUST call {@link RMQCollector#setCorrelationId(String)} with
	 * the correlation ID of the message if checkpointing and UseCorrelationID (in the RMQSource constructor) were enabled
	 * the {@link RMQSource}.
	 * @param envelope
	 * @param properties
	 * @param body
	 * @throws IOException
	 */
	public  void processMessage(Envelope envelope, AMQP.BasicProperties properties, byte[] body, RMQCollector collector) throws IOException;

	/**
	 * Method to decide whether the element signals the end of the stream. If
	 * true is returned the element won't be emitted.
	 *
	 * @param nextElement The element to test for the end-of-stream signal.
	 * @return True, if the element signals end of stream, false otherwise.
	 */
	public boolean isEndOfStream(T nextElement);

	/**
	 * The {@link TypeInformation} for the deserialized T.
	 * As an example the proper implementation of this method if T is a String is:
	 * {@code return TypeExtractor.getForClass(String.class)}
	 * @return TypeInformation
	 */
	public TypeInformation<T> getProducedType();
}
