/*
 *
 *  *
 *  * Licensed to the Apache Software Foundation (ASF) under one
 *  * or more contributor license agreements.  See the NOTICE file
 *  * distributed with this work for additional information
 *  * regarding copyright ownership.  The ASF licenses this file
 *  * to you under the Apache License, Version 2.0 (the
 *  * "License"); you may not use this file except in compliance
 *  * with the License.  You may obtain a copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing,
 *  * software distributed under the License is distributed on an
 *  * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  * KIND, either express or implied.  See the License for the
 *  * specific language governing permissions and limitations
 *  * under the License.
 *  *
 *
 */

package org.apache.airavata.security.userstore;

import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.InputStream;

/**
 * Test class for session DB authenticator.
 */
public class SessionDBUserStoreTest extends TestCase {

    private SessionDBUserStore sessionDBUserStore = new SessionDBUserStore();

    private InputStream configurationFileStream
            = this.getClass().getClassLoader().getResourceAsStream("session-authenticator.xml");

    public void setUp() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(configurationFileStream);
        doc.getDocumentElement().normalize();

        NodeList specificConfigurations = doc.getElementsByTagName("specificConfigurations");
        sessionDBUserStore.configure(specificConfigurations.item(0));
    }

    public void testAuthenticate() throws Exception {
        assertTrue(sessionDBUserStore.authenticate("1234"));

    }

    public void testAuthenticateFailure() throws Exception  {
        assertFalse(sessionDBUserStore.authenticate("12345"));
    }
}
