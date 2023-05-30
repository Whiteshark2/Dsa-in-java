package PrQueueHeap;


import java.util.ArrayList;

public class Priority_Queue<T>{
	private ArrayList<Element<T>> heap;
	int size;
	
	public Priority_Queue() {
		heap=new ArrayList<Element<T>>();
		size=0;
	}
	public void insert(T value,int priority) {
		Element<T> e=new Element<>(value, priority);
		heap.add(e);
		size++;
		int childIndex=heap.size()-1;
		int parentIndex=(childIndex-1)/2;
		while(childIndex>0) {
			if(heap.get(childIndex).priority<heap.get(parentIndex).priority) {
				Element <T> temp=heap.get(childIndex);
				heap.set(childIndex,heap.get(parentIndex));
				heap.set(parentIndex,temp );
				childIndex=parentIndex;
				parentIndex=(childIndex-1)/2;
			}
			else {
				return;
			}
		}
		
	}
	public T getMin() throws PriorityQueueException {
		if(isEmpty()) {
			throw new PriorityQueueException();
		}
		return heap.get(0).value;
	
	}
	public T removeMin() throws PriorityQueueException {
		if(isEmpty()) {
			throw new PriorityQueueException();
		}
		Element<T> remove=heap.get(0);
		T ans=remove.value;
		
		heap.set(0, heap.get(size()-1));
		heap.remove(heap.size()-1);
		size--;
		int parentIndex=0;
		int leftIndex=2*parentIndex+1;
		int rightIndex=2*parentIndex+2;
		
		while(leftIndex<heap.size()) {
			int minIndex=parentIndex;
			if(heap.get(leftIndex).priority<heap.get(minIndex).priority) {
				minIndex=leftIndex;
			}
			if(rightIndex<heap.size() && heap.get(rightIndex).priority<heap.get(minIndex).priority) {
				minIndex=rightIndex;
			}
			if(minIndex==parentIndex) {
				break;
			}
			Element<T> temp=heap.get(minIndex);
			heap.set(minIndex, heap.get(parentIndex));
			heap.set(parentIndex, temp);
			parentIndex=minIndex;
			leftIndex=2*parentIndex+1;
			rightIndex=2*parentIndex+2;
		}
		return ans;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
}
