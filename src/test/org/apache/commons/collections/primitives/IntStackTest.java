/*
 * Copyright 2004 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections.primitives;

import java.util.EmptyStackException;

import junit.framework.TestCase;

/**
 * Tests the IntStack class.
 *
 * @author Apache Directory Project
 * @since Commons Primitives 1.1
 * @version $Revision: 1.2 $ $Date: 2004/04/14 22:23:40 $
 */
public class IntStackTest extends TestCase
{
    IntStack stack = null ;
    
    
    /**
     * Runs the test. 
     * 
     * @param args nada
     */
    public static void main( String[] args )
    {
        junit.textui.TestRunner.run( IntStackTest.class ) ;
    }

    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp() ;
        stack = new IntStack() ;
    }
    
    
    /**
     * Constructor for IntStackTest.
     * @param arg0
     */
    public IntStackTest( String arg0 )
    {
        super( arg0 ) ;
    }

    
    public void testEmpty()
    {
        assertTrue( "Newly created stacks should be empty", stack.empty() ) ;
        stack.push( 0 ) ;
        assertFalse( "Stack with item should not be empty", stack.empty() ) ;
        stack.pop() ;
        assertTrue( "Stack last int popped should be empty", stack.empty() ) ;
    }

    
    public void testPeek()
    {
        try
        {
            stack.peek() ;
            throw new AssertionError( 
                    "Peek should have thrown an EmptyStackException" ) ;
        }
        catch( EmptyStackException e )
        {
            assertNotNull( "EmptyStackException should not be null", e ) ;
        }
        
        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ii ) ;
            assertEquals( ii, stack.peek() ) ;
        }
    }

    
    public void testPop()
    {
        try
        {
            stack.pop() ;
            throw new AssertionError( 
                    "Pop should have thrown an EmptyStackException" ) ;
        }
        catch( EmptyStackException e )
        {
            assertNotNull( "EmptyStackException should not be null", e ) ;
        }
        
        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ii ) ;
            assertEquals( ii, stack.pop() ) ;
        }

        for( int ii = 0; ii < 10; ii++ )
        {    
            stack.push( ii ) ;
        }
        for( int ii = 10; ii < 0; ii-- )
        {    
            stack.push( ii ) ;
            assertEquals( ii, stack.pop() ) ;
        }
    }

    
    public void testPush()
    {
        stack.push( 0 ) ;
        stack.push( 0 ) ;
        assertFalse( stack.empty() ) ;
        assertEquals( 0, stack.pop() ) ;
        assertEquals( 0, stack.pop() ) ;
    }

    
    public void testSearch()
    {
        stack.push( 0 ) ;
        stack.push( 1 ) ;
        assertEquals( 2, stack.search( 0 ) ) ;
        stack.push( 0 ) ;
        assertEquals( 1, stack.search( 0 ) ) ;
        stack.push( 0 ) ;
        assertEquals( 3, stack.search( 1 ) ) ;
        assertEquals( -1, stack.search( 44 ) ) ;
    }
}
