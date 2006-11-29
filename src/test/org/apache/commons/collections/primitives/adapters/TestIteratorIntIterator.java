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

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.collections.primitives.IntIterator;
import org.apache.commons.collections.primitives.TestIntIterator;

/**
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class TestIteratorIntIterator extends TestIntIterator {

    // conventional
    // ------------------------------------------------------------------------

    public TestIteratorIntIterator(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestIteratorIntIterator.class);
    }

    // collections testing framework
    // ------------------------------------------------------------------------

    public IntIterator makeEmptyIntIterator() {
        return IteratorIntIterator.wrap(makeEmptyList().iterator());
    }
    
    public IntIterator makeFullIntIterator() {
        return IteratorIntIterator.wrap(makeFullList().iterator());
    }

    public List makeEmptyList() {
        return new ArrayList();
    }
    
    protected List makeFullList() {
        List list = makeEmptyList();
        int[] elts = getFullElements();
        for(int i=0;i<elts.length;i++) {
            list.add(new Integer(elts[i]));
        }
        return list;
    }
    
    public int[] getFullElements() {
        return new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }
    
    // tests
    // ------------------------------------------------------------------------


}
