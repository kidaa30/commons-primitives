/*
 * Copyright 2002-2005 The Apache Software Foundation
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

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Abstract base class for {@link IntList}s backed 
 * by random access structures like arrays.
 * <p />
 * Read-only subclasses must override {@link #get}
 * and {@link #size}.  Mutable subclasses
 * should also override {@link #set}.  Variably-sized
 * subclasses should also override {@link #add(int)} 
 * and {@link #removeElementAt}.  All other methods
 * have at least some base implementation derived from 
 * these.  Subclasses may choose to override these methods
 * to provide a more efficient implementation.
 * 
 * @since Commons Primitives 1.0
 * @version $Revision$ $Date$
 * 
 * @author Rodney Waldhoff 
 */
public abstract class RandomAccessIntList extends AbstractIntCollection implements IntList {

    // constructors
    //-------------------------------------------------------------------------

    /** Constructs an empty list. */
    protected RandomAccessIntList() { 
    }    

    // fully abstract methods
    //-------------------------------------------------------------------------
    
    public abstract int get(int index);
    public abstract int size();

    // unsupported in base
    //-------------------------------------------------------------------------
    
    /** 
     * Unsupported in this implementation. 
     * @throws UnsupportedOperationException since this method is not supported
     */
    public int removeElementAt(int index) {
        throw new UnsupportedOperationException();
    }
    
    /** 
     * Unsupported in this implementation. 
     * @throws UnsupportedOperationException since this method is not supported
     */
    public int set(int index, int element) {
        throw new UnsupportedOperationException();
    }
        
    /** 
     * Unsupported in this implementation. 
     * @throws UnsupportedOperationException since this method is not supported
     */
    public void add(int index, int element) {
        throw new UnsupportedOperationException();
    }

    //-------------------------------------------------------------------------

    // javadocs here are inherited
    
    public boolean add(int element) {
        add(size(),element);
        return true;
    }

    public boolean addAll(int index, IntCollection collection) {
        boolean modified = false;
        for(IntIterator iter = collection.iterator(); iter.hasNext(); ) {
            add(index++,iter.next());
            modified = true;
        }
        return modified;
    }

    public int indexOf(int element) {
        int i = 0;
        for(IntIterator iter = iterator(); iter.hasNext(); ) {
            if(iter.next() == element) { 
                return i;
            } else {
                i++;
            }
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        for(IntListIterator iter = listIterator(size()); iter.hasPrevious(); ) {
            if(iter.previous() == element) {
                return iter.nextIndex();
            }
        }
        return -1;
    }

    public IntIterator iterator() {
        return listIterator();
    }

    public IntListIterator listIterator() {
        return listIterator(0);
    }

    public IntListIterator listIterator(int index) {
        return new RandomAccessIntListIterator(this,index);            
    }

    public IntList subList(int fromIndex, int toIndex) {
        return new RandomAccessIntSubList(this,fromIndex,toIndex);
    }

    public boolean equals(Object that) {
        if(this == that) { 
            return true; 
        } else if(that instanceof IntList) {
            IntList thatList = (IntList)that;
            if(size() != thatList.size()) {
                return false;
            }
            for(IntIterator thatIter = thatList.iterator(), thisIter = iterator(); thisIter.hasNext();) {
                if(thisIter.next() != thatIter.next()) { 
                    return false; 
                }
            }
            return true;
        } else {
            return false;
        }        
    }
    
    public int hashCode() {
        int hash = 1;
        for(IntIterator iter = iterator(); iter.hasNext(); ) {
            hash = 31*hash + iter.next();
        }
        return hash;
    }
    
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[");
        for(IntIterator iter = iterator(); iter.hasNext();) {
            buf.append(iter.next());
            if(iter.hasNext()) {
                buf.append(", ");
            }
        }
        buf.append("]");
        return buf.toString();
    }
    
    // protected utilities
    //-------------------------------------------------------------------------
    
    /** Get my count of structural modifications. */
    protected int getModCount() {
        return _modCount;
    }

    /** Increment my count of structural modifications. */
    protected void incrModCount() {
        _modCount++;
    }

    // attributes
    //-------------------------------------------------------------------------
    
    private int _modCount = 0;

    // inner classes
    //-------------------------------------------------------------------------
    
    private static class ComodChecker {
        ComodChecker(RandomAccessIntList source) {
            _source = source;  
            resyncModCount();             
        }
        
        protected RandomAccessIntList getList() {
            return _source;
        }
        
        protected void assertNotComodified() throws ConcurrentModificationException {
            if(_expectedModCount != getList().getModCount()) {
                throw new ConcurrentModificationException();
            }
        }
            
        protected void resyncModCount() {
            _expectedModCount = getList().getModCount();
        }
        
        private RandomAccessIntList _source = null;
        private int _expectedModCount = -1;
    }
    
    protected static class RandomAccessIntListIterator extends ComodChecker implements IntListIterator {
        RandomAccessIntListIterator(RandomAccessIntList list, int index) {
            super(list);
            if(index < 0 || index > getList().size()) {
                throw new IndexOutOfBoundsException("Index " + index + " not in [0," + getList().size() + ")");
            } else {
                _nextIndex = index;
                resyncModCount();
            }
        }
            
        public boolean hasNext() {
            assertNotComodified();
            return _nextIndex < getList().size();
        }
        
        public boolean hasPrevious() {
            assertNotComodified();
            return _nextIndex > 0;
        }
        
        public int nextIndex() {
            assertNotComodified();
            return _nextIndex;
        }
        
        public int previousIndex() {
            assertNotComodified();
            return _nextIndex - 1;
        }
        
        public int next() {
            assertNotComodified();
            if(!hasNext()) {
                throw new NoSuchElementException();
            } else {
                int val = getList().get(_nextIndex);
                _lastReturnedIndex = _nextIndex;
                _nextIndex++;
                return val;
            }
        }
        
        public int previous() {
            assertNotComodified();
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            } else {
                int val = getList().get(_nextIndex-1);
                _lastReturnedIndex = _nextIndex-1;
                _nextIndex--;
                return val;
            }
        }
        
        public void add(int value) {
            assertNotComodified();
            getList().add(_nextIndex,value);
            _nextIndex++;
            _lastReturnedIndex = -1;
            resyncModCount();
        }
    
        public void remove() {
            assertNotComodified();
            if (_lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }
            if (_lastReturnedIndex == _nextIndex) {
                // remove() following previous()
                getList().removeElementAt(_lastReturnedIndex);
            } else {
                // remove() following next()
                getList().removeElementAt(_lastReturnedIndex);
                _nextIndex--;
            }
            _lastReturnedIndex = -1;
            resyncModCount();
        }
        
        public void set(int value) {
            assertNotComodified();
            if(-1 == _lastReturnedIndex) {
                throw new IllegalStateException();
            } else {
                getList().set(_lastReturnedIndex,value);
                resyncModCount();
            }
        }
        
        private int _nextIndex = 0;
        private int _lastReturnedIndex = -1;        
    }   

    protected static class RandomAccessIntSubList extends RandomAccessIntList implements IntList {
        RandomAccessIntSubList(RandomAccessIntList list, int fromIndex, int toIndex) {
            if(fromIndex < 0 || toIndex > list.size()) {
                throw new IndexOutOfBoundsException();
            } else if(fromIndex > toIndex) {
                throw new IllegalArgumentException();                
            } else {
                _list = list;
                _offset = fromIndex;
                _limit = toIndex - fromIndex;
                _comod = new ComodChecker(list);
                _comod.resyncModCount();
            }            
        }
    
        public int get(int index) {
            checkRange(index);
            _comod.assertNotComodified();
            return _list.get(toUnderlyingIndex(index));
        }
    
        public int removeElementAt(int index) {
            checkRange(index);
            _comod.assertNotComodified();
            int val = _list.removeElementAt(toUnderlyingIndex(index));
            _limit--;
            _comod.resyncModCount();
            incrModCount();
            return val;
        }
    
        public int set(int index, int element) {
            checkRange(index);
            _comod.assertNotComodified();
            int val = _list.set(toUnderlyingIndex(index),element);
            incrModCount();
            _comod.resyncModCount();
            return val;
        }
    
        public void add(int index, int element) {
            checkRangeIncludingEndpoint(index);
            _comod.assertNotComodified();
             _list.add(toUnderlyingIndex(index),element);
            _limit++;
            _comod.resyncModCount();
            incrModCount();
        }
    
        public int size() {
            _comod.assertNotComodified();
            return _limit;
        }
    
        private void checkRange(int index) {
            if(index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + ")");
            }
        }
          
        private void checkRangeIncludingEndpoint(int index) {
            if(index < 0 || index > size()) {
                throw new IndexOutOfBoundsException("index " + index + " not in [0," + size() + "]");
            }
        }
          
        private int toUnderlyingIndex(int index) {
            return (index + _offset);
        }
        
        private int _offset = 0;        
        private int _limit = 0; 
        private RandomAccessIntList _list = null;
        private ComodChecker _comod = null;
    
    }
}

