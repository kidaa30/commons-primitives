/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.primitives.adapters;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.collections.BulkTest;
import org.apache.commons.collections.primitives.CharList;
import org.apache.commons.collections.primitives.TestCharList;

/**
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class TestListCharList extends TestCharList {

    // conventional
    // ------------------------------------------------------------------------

    public TestListCharList(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = BulkTest.makeSuite(TestListCharList.class);
        return suite;
    }

    // collections testing framework
    // ------------------------------------------------------------------------

    /**
     * @see org.apache.commons.collections.primitives.TestCharList#makeEmptyCharList()
     */
    protected CharList makeEmptyCharList() {
        return new ListCharList(new ArrayList());
    }
    
    public String[] ignoredTests() {
        // sublists are not serializable
        return new String[] { 
            "TestListCharList.bulkTestSubList.testFullListSerialization",
            "TestListCharList.bulkTestSubList.testEmptyListSerialization",
            "TestListCharList.bulkTestSubList.testCanonicalEmptyCollectionExists",
            "TestListCharList.bulkTestSubList.testCanonicalFullCollectionExists",
            "TestListCharList.bulkTestSubList.testEmptyListCompatibility",
            "TestListCharList.bulkTestSubList.testFullListCompatibility",
            "TestListCharList.bulkTestSubList.testSerializeDeserializeThenCompare",
            "TestListCharList.bulkTestSubList.testSimpleSerialization"
        };
    }

    // tests
    // ------------------------------------------------------------------------

    /** @TODO need to add serialized form to cvs */
    public void testCanonicalEmptyCollectionExists() {
        // XXX FIX ME XXX
        // need to add a serialized form to cvs
    }

    public void testCanonicalFullCollectionExists() {
        // XXX FIX ME XXX
        // need to add a serialized form to cvs
    }

    public void testEmptyListCompatibility() {
        // XXX FIX ME XXX
        // need to add a serialized form to cvs
    }

    public void testFullListCompatibility() {
        // XXX FIX ME XXX
        // need to add a serialized form to cvs
    }
    public void testWrapNull() {
        assertNull(ListCharList.wrap(null));
    }
    
    public void testWrapSerializable() {
        CharList list = ListCharList.wrap(new ArrayList());
        assertNotNull(list);
        assertTrue(list instanceof Serializable);
    }
    
    public void testWrapNonSerializable() {
        CharList list = ListCharList.wrap(new AbstractList() { 
            public Object get(int i) { throw new IndexOutOfBoundsException(); } 
            public int size() { return 0; } 
        });
        assertNotNull(list);
        assertTrue(!(list instanceof Serializable));
    }

}
