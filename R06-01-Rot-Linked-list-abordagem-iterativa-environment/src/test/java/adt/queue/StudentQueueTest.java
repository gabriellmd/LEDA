package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.queue.QueueDoubleLinkedListImpl;
import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class StudentQueueTest {

	public QueueDoubleLinkedListImpl<Integer> queue1;
	public QueueDoubleLinkedListImpl<Integer> queue2;
	public QueueDoubleLinkedListImpl<Integer> queue3;
	public QueueDoubleLinkedListImpl<Integer> circular;

	@Before
	public void setUp() throws QueueOverflowException, StackOverflowException { //, StackOverflowException

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueDoubleLinkedListImpl<>(4);
		queue2 = new QueueDoubleLinkedListImpl<>(2);
		queue3 = new QueueDoubleLinkedListImpl<>(4);
		circular = new QueueDoubleLinkedListImpl<>(3);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testHead() throws StackOverflowException, StackUnderflowException { //throws StackOverflowException, StackUnderflowException
		assertEquals(new Integer(1), queue1.head());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(queue1.isEmpty());
		assertTrue(queue3.isEmpty());
		assertTrue(circular.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(queue1.isFull());
		assertFalse(circular.isFull());
	}

	@Test
	public void testEnqueue() throws StackOverflowException { //throws StackOverflowException
		try {
			queue1.enqueue(new Integer(5));
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException, StackOverflowException { //, StackOverflowException
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() throws StackUnderflowException, StackOverflowException { // throws StackUnderflowException, StackOverflowException
		try {
			assertEquals(new Integer(1), queue1.dequeue());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException, StackUnderflowException, StackOverflowException { //, StackUnderflowException, StackOverflowException
		assertEquals(new Integer(1), queue3.dequeue()); // vai depender do
														// tamanho que a fial
														// foi iniciada!!!
	}
	
	@Test
	public void testCircularEnqueue() throws QueueOverflowException, QueueUnderflowException, StackOverflowException, StackUnderflowException{ //, StackOverflowException, StackUnderflowException
		
		circular.enqueue(new Integer(5));
		circular.enqueue(new Integer(4));
		circular.enqueue(new Integer(3));
		assertTrue(circular.isFull());
		assert(circular.dequeue() == 5);
		assert(circular.dequeue() == 4);
		assert(circular.dequeue() == 3);
	}
	
}