/*
 *  Copyright (c) 2020 Oracle and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.helidon.examples.sockshop.payment.jpa;

import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;
import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.jpa.JpaPaymentRepository}.
 */
public class JpaPaymentRepositoryIT extends PaymentRepositoryTest {
    protected static Server SERVER;

    /**
     * This will start the application on ephemeral port to avoid port conflicts.
     * We can discover the actual port by calling {@link io.helidon.microprofile.server.Server#port()} method afterwards.
     */
    @BeforeAll
    static void startServer() {
        // disable global tracing so we can start server in multiple test suites
        System.setProperty("tracing.global", "false");
        SERVER = Server.builder().port(0).build().start();
    }

    /**
     * Stop the server, as we cannot have multiple servers started at the same time.
     */
    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @Override
    protected TestPaymentRepository getPaymentRepository() {
        return SERVER.cdiContainer().select(TestPaymentRepository.class).get();
    }
}
