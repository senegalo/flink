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

import java.util.List;

/**
 * A wrapper class around a deserialized AMQP message.
 * @param <OUT>
 */

public class RMQDeserializedMessage<OUT> {
	private List<OUT> records;
	private String correlationID;

	/**
	 * It takes as input the list of record(s) extracted from the AMQP message and the correlation ID.
	 * @param records
	 * @param correlationID
	 */
	public RMQDeserializedMessage(List<OUT> records, String correlationID) {
		this.records = records;
		this.correlationID = correlationID;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public List<OUT> getMessages() {
		return records;
	}
}
