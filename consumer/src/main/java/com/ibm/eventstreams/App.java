/*
 * Copyright 2020 IBM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/*
 * Licensed Materials - Property of IBM
 * (c) Copyright IBM Corp. 2020
 */
package com.ibm.eventstreams;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;

public class App {

    private static final String KEY = "coding-challenge";
    private static final String TOPIC = "event-streams-coding-challenge";

    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
        }
        String bootstrapServer = args[0];
        String apikey = args[1];
        Map<String, Object> configs = getConfigs(bootstrapServer, apikey);
        try (Consumer<String, String> consumer = new KafkaConsumer<>(configs);) {
            consumer.subscribe(Collections.singletonList(TOPIC), new ConsumerRebalanceListener() {

                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    // TODO Move position to beginning of partition
                }
            });
            // TODO: Add consumer poll loop
            // TODO: Find the record whose key is equal to KEY ("coding-challenge")
            // TODO: Print the record value to discover the secret message
        }
    }

    private static void usage() {
        System.out.println("Usage: <kafka_brokers_sasl> <api_key>");
    }

    private static Map<String, Object> getConfigs(String bootstrapServer, String apikey) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        configs.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configs.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"token\" password=\"" + apikey + "\";");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return configs;
    }
}
