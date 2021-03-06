/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.kubernetes.deprecated;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kubernetes.consumer.KubernetesPodsConsumerTest;

@Deprecated
public class DeprecatedKubernetesPodsConsumerTest extends KubernetesPodsConsumerTest {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:list").toF("kubernetes://%s?oauthToken=%s&category=pods&operation=listPods", host,
                        authToken);
                from("direct:listByLabels")
                        .toF("kubernetes://%s?oauthToken=%s&category=pods&operation=listPodsByLabels", host, authToken);
                from("direct:getPod").toF("kubernetes://%s?oauthToken=%s&category=pods&operation=getPod", host,
                        authToken);
                from("direct:createPod").toF("kubernetes://%s?oauthToken=%s&category=pods&operation=createPod", host,
                        authToken);
                from("direct:deletePod").toF("kubernetes://%s?oauthToken=%s&category=pods&operation=deletePod", host,
                        authToken);
                fromF("kubernetes://%s?oauthToken=%s&category=pods&namespace=default&labelKey=this&labelValue=rocks", host, authToken)
                        .process(new KubernertesProcessor()).to(mockResultEndpoint);
            }
        };
    }

}
