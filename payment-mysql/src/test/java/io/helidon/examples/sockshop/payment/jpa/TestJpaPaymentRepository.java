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

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;

import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Alternative
@Priority(APPLICATION + 5)
public class TestJpaPaymentRepository extends JpaPaymentRepository implements TestPaymentRepository {
    @Override
    @Transactional
    public void clear() {
        em.createQuery("delete from Authorization").executeUpdate();
    }
}
