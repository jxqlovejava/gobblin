/*
 * Copyright (C) 2014-2016 LinkedIn Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied.
 */

package gobblin.kafka.serialize;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

import gobblin.kafka.schemareg.KafkaSchemaRegistry;
import gobblin.kafka.schemareg.KafkaSchemaRegistryFactory;
import gobblin.kafka.schemareg.SchemaRegistryException;


/**
 * The LinkedIn Avro Deserializer (works with records serialized by the {@link LiAvroSerializer})
 */
@Slf4j
public class LiAvroDeserializer extends LiAvroDeserializerBase implements Deserializer<GenericRecord> {
  public LiAvroDeserializer(KafkaSchemaRegistry<MD5Digest, Schema> schemaRegistry)
  {
    super(schemaRegistry);
  }

  /**
   *
   * @param topic topic associated with the data
   * @param data serialized bytes
   * @return deserialized object
   */
  @Override
  public GenericRecord deserialize(String topic, byte[] data) {
    try {
      return super.deserialize(topic, data);
    }
    catch (gobblin.kafka.serialize.SerializationException e) {
      throw new SerializationException("Error during Deserialization", e);
    }
  }
}
