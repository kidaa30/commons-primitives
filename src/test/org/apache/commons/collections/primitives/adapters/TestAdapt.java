/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//primitives/src/test/org/apache/commons/collections/primitives/adapters/TestAdapt.java,v 1.2 2003/12/04 23:19:22 rwaldhoff Exp $
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.collections.primitives.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.collections.primitives.ArrayByteList;
import org.apache.commons.collections.primitives.ArrayCharList;
import org.apache.commons.collections.primitives.ArrayDoubleList;
import org.apache.commons.collections.primitives.ArrayFloatList;
import org.apache.commons.collections.primitives.ArrayIntList;
import org.apache.commons.collections.primitives.ArrayLongList;
import org.apache.commons.collections.primitives.ArrayShortList;
import org.apache.commons.collections.primitives.ByteCollection;
import org.apache.commons.collections.primitives.ByteIterator;
import org.apache.commons.collections.primitives.ByteList;
import org.apache.commons.collections.primitives.ByteListIterator;
import org.apache.commons.collections.primitives.CharCollection;
import org.apache.commons.collections.primitives.CharIterator;
import org.apache.commons.collections.primitives.CharList;
import org.apache.commons.collections.primitives.CharListIterator;
import org.apache.commons.collections.primitives.DoubleCollection;
import org.apache.commons.collections.primitives.DoubleIterator;
import org.apache.commons.collections.primitives.DoubleList;
import org.apache.commons.collections.primitives.DoubleListIterator;
import org.apache.commons.collections.primitives.FloatCollection;
import org.apache.commons.collections.primitives.FloatIterator;
import org.apache.commons.collections.primitives.FloatList;
import org.apache.commons.collections.primitives.FloatListIterator;
import org.apache.commons.collections.primitives.IntCollection;
import org.apache.commons.collections.primitives.IntIterator;
import org.apache.commons.collections.primitives.IntList;
import org.apache.commons.collections.primitives.IntListIterator;
import org.apache.commons.collections.primitives.LongCollection;
import org.apache.commons.collections.primitives.LongIterator;
import org.apache.commons.collections.primitives.LongList;
import org.apache.commons.collections.primitives.LongListIterator;
import org.apache.commons.collections.primitives.ShortCollection;
import org.apache.commons.collections.primitives.ShortIterator;
import org.apache.commons.collections.primitives.ShortList;
import org.apache.commons.collections.primitives.ShortListIterator;

/**
 * @version $Revision: 1.2 $ $Date: 2003/12/04 23:19:22 $
 * @author Rodney Waldhoff
 */
public class TestAdapt extends TestCase {

    // conventional
    // ------------------------------------------------------------------------

    public TestAdapt(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(TestAdapt.class);
    }

    // tests
    // ------------------------------------------------------------------------

    public void testHasPublicConstructorForReflectionBasedAPIs() {
        assertNotNull(new Adapt());
    }
    
    // to object based
    //---------------------------------------------------------------
    
    public void testToCollection() {
        assertNull(Adapt.toCollection((ArrayByteList)null));
        assertTrue(Adapt.toCollection(new ArrayByteList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayCharList)null));
        assertTrue(Adapt.toCollection(new ArrayCharList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayDoubleList)null));
        assertTrue(Adapt.toCollection(new ArrayDoubleList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayFloatList)null));
        assertTrue(Adapt.toCollection(new ArrayFloatList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayIntList)null));
        assertTrue(Adapt.toCollection(new ArrayIntList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayLongList)null));
        assertTrue(Adapt.toCollection(new ArrayLongList()) instanceof Collection);
        assertNull(Adapt.toCollection((ArrayShortList)null));
        assertTrue(Adapt.toCollection(new ArrayShortList()) instanceof Collection);
    }
    
    public void testToList() {
        assertNull(Adapt.toList((ArrayByteList)null));
        assertTrue(Adapt.toList(new ArrayByteList()) instanceof List);
        assertNull(Adapt.toList((ArrayCharList)null));
        assertTrue(Adapt.toList(new ArrayCharList()) instanceof List);
        assertNull(Adapt.toList((ArrayDoubleList)null));
        assertTrue(Adapt.toList(new ArrayDoubleList()) instanceof List);
        assertNull(Adapt.toList((ArrayFloatList)null));
        assertTrue(Adapt.toList(new ArrayFloatList()) instanceof List);
        assertNull(Adapt.toList((ArrayIntList)null));
        assertTrue(Adapt.toList(new ArrayIntList()) instanceof List);
        assertNull(Adapt.toList((ArrayLongList)null));
        assertTrue(Adapt.toList(new ArrayLongList()) instanceof List);
        assertNull(Adapt.toList((ArrayShortList)null));
        assertTrue(Adapt.toList(new ArrayShortList()) instanceof List);
    }
    
    public void testToIterator() {
        assertNull(Adapt.toIterator((ByteIterator)null));
        assertTrue(Adapt.toIterator(new ArrayByteList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((CharIterator)null));
        assertTrue(Adapt.toIterator(new ArrayCharList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((DoubleIterator)null));
        assertTrue(Adapt.toIterator(new ArrayDoubleList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((FloatIterator)null));
        assertTrue(Adapt.toIterator(new ArrayFloatList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((IntIterator)null));
        assertTrue(Adapt.toIterator(new ArrayIntList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((LongIterator)null));
        assertTrue(Adapt.toIterator(new ArrayLongList().iterator()) instanceof Iterator);
        assertNull(Adapt.toIterator((ShortIterator)null));
        assertTrue(Adapt.toIterator(new ArrayShortList().iterator()) instanceof Iterator);
    }

    public void testToListIterator() {
        assertNull(Adapt.toListIterator((ByteListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayByteList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((CharListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayCharList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((DoubleListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayDoubleList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((FloatListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayFloatList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((IntListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayIntList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((LongListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayLongList().listIterator()) instanceof ListIterator);
        assertNull(Adapt.toListIterator((ShortListIterator)null));
        assertTrue(Adapt.toListIterator(new ArrayShortList().listIterator()) instanceof ListIterator);
    }

    // to byte based
    //---------------------------------------------------------------
    
    public void testToByteType() {
        assertTrue(Adapt.toByteCollection(new ArrayList()) instanceof ByteCollection);
        assertTrue(Adapt.toByteList(new ArrayList()) instanceof ByteList);
        assertTrue(Adapt.toByteIterator(new ArrayList().iterator()) instanceof ByteIterator);
        assertTrue(Adapt.toByteListIterator(new ArrayList().listIterator()) instanceof ByteListIterator);
    }

    public void testToByteTypeFromNull() {
        assertNull(Adapt.toByteCollection(null));
        assertNull(Adapt.toByteList(null));
        assertNull(Adapt.toByteIterator(null));
        assertNull(Adapt.toByteListIterator(null));
    }

    // to char based
    //---------------------------------------------------------------
    
    public void testToCharType() {
        assertTrue(Adapt.toCharCollection(new ArrayList()) instanceof CharCollection);
        assertTrue(Adapt.toCharList(new ArrayList()) instanceof CharList);
        assertTrue(Adapt.toCharIterator(new ArrayList().iterator()) instanceof CharIterator);
        assertTrue(Adapt.toCharListIterator(new ArrayList().listIterator()) instanceof CharListIterator);
    }

    public void testToCharTypeFromNull() {
        assertNull(Adapt.toCharCollection(null));
        assertNull(Adapt.toCharList(null));
        assertNull(Adapt.toCharIterator(null));
        assertNull(Adapt.toCharListIterator(null));
    }

    // to double based
    //---------------------------------------------------------------
    
    public void testToDoubleType() {
        assertTrue(Adapt.toDoubleCollection(new ArrayList()) instanceof DoubleCollection);
        assertTrue(Adapt.toDoubleList(new ArrayList()) instanceof DoubleList);
        assertTrue(Adapt.toDoubleIterator(new ArrayList().iterator()) instanceof DoubleIterator);
        assertTrue(Adapt.toDoubleListIterator(new ArrayList().listIterator()) instanceof DoubleListIterator);
    }

    public void testToDoubleTypeFromNull() {
        assertNull(Adapt.toDoubleCollection(null));
        assertNull(Adapt.toDoubleList(null));
        assertNull(Adapt.toDoubleIterator(null));
        assertNull(Adapt.toDoubleListIterator(null));
    }

    // to float based
    //---------------------------------------------------------------
    
    public void testToFloatType() {
        assertTrue(Adapt.toFloatCollection(new ArrayList()) instanceof FloatCollection);
        assertTrue(Adapt.toFloatList(new ArrayList()) instanceof FloatList);
        assertTrue(Adapt.toFloatIterator(new ArrayList().iterator()) instanceof FloatIterator);
        assertTrue(Adapt.toFloatListIterator(new ArrayList().listIterator()) instanceof FloatListIterator);
    }

    public void testToFloatTypeFromNull() {
        assertNull(Adapt.toFloatCollection(null));
        assertNull(Adapt.toFloatList(null));
        assertNull(Adapt.toFloatIterator(null));
        assertNull(Adapt.toFloatListIterator(null));
    }

    // to int based
    //---------------------------------------------------------------
    
    public void testToIntType() {
        assertTrue(Adapt.toIntCollection(new ArrayList()) instanceof IntCollection);
        assertTrue(Adapt.toIntList(new ArrayList()) instanceof IntList);
        assertTrue(Adapt.toIntIterator(new ArrayList().iterator()) instanceof IntIterator);
        assertTrue(Adapt.toIntListIterator(new ArrayList().listIterator()) instanceof IntListIterator);
    }

    public void testToIntTypeFromNull() {
        assertNull(Adapt.toIntCollection(null));
        assertNull(Adapt.toIntList(null));
        assertNull(Adapt.toIntIterator(null));
        assertNull(Adapt.toIntListIterator(null));
    }

    // to long based
    //---------------------------------------------------------------
    
    public void testToLongType() {
        assertTrue(Adapt.toLongCollection(new ArrayList()) instanceof LongCollection);
        assertTrue(Adapt.toLongList(new ArrayList()) instanceof LongList);
        assertTrue(Adapt.toLongIterator(new ArrayList().iterator()) instanceof LongIterator);
        assertTrue(Adapt.toLongListIterator(new ArrayList().listIterator()) instanceof LongListIterator);
    }

    public void testToLongTypeFromNull() {
        assertNull(Adapt.toLongCollection(null));
        assertNull(Adapt.toLongList(null));
        assertNull(Adapt.toLongIterator(null));
        assertNull(Adapt.toLongListIterator(null));
    }

    // to short based
    //---------------------------------------------------------------
    
    public void testToShortType() {
        assertTrue(Adapt.toShortCollection(new ArrayList()) instanceof ShortCollection);
        assertTrue(Adapt.toShortList(new ArrayList()) instanceof ShortList);
        assertTrue(Adapt.toShortIterator(new ArrayList().iterator()) instanceof ShortIterator);
        assertTrue(Adapt.toShortListIterator(new ArrayList().listIterator()) instanceof ShortListIterator);
    }

    public void testToShortTypeFromNull() {
        assertNull(Adapt.toShortCollection(null));
        assertNull(Adapt.toShortList(null));
        assertNull(Adapt.toShortIterator(null));
        assertNull(Adapt.toShortListIterator(null));
    }
}
