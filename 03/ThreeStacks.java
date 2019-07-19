public class ThreeStacks {

	private int[] array;
	private Stack stackA, stackB, stackC;
	private int maxStackLength;

	public ThreeStacks(int maxStackLength) {
		this.maxStackLength = maxStackLength;
		array  = new int[maxStackLength * 3];
		stackA = new Stack(0, 0);
		stackB = new Stack(maxStackLength, 0);
		stackC = new Stack(maxStackLength * 2, 0);
	}

	public int peek(int stackNumber) throws EmptyStackException{
		Stack stack = getStack(stackNumber);
		if (stack.isEmpty()) throw new EmptyStackException();
		return array[stack.startPos + stack.length - 1];
	}

	// Pop the pop element from stack stackNumber
	public int pop(int stackNumber) throws EmptyStackException{
		Stack stack 	= getStack(stackNumber);
		if (stack.isEmpty()) throw new EmptyStackException();
		int index 		= stack.startPos + stack.length - 1;
		int element 	= array[index];
		array[index] 	= 0;
		stack.length--;
		return element;
	}

	// Push item to the top of stack stackNumber
	public void push(int stackNumber, int item) throws FullStackException {
		Stack stack  = getStack(stackNumber);
		if (stack.length == maxStackLength) throw new FullStackException();
		int index    = stack.startPos + stack.length;
		array[index] = item;
		stack.length++;
	}

	private Stack getStack(int stackNumber) throws InvalidStackNumberException {
		switch (stackNumber) {
			case 1 : return stackA;
			case 2 : return stackB;
			case 3 : return stackC;
			default: throw new InvalidStackNumberException();
		}
	}

	public boolean stackIsEmpty(int i) {
		return getStack(i).isEmpty();
	}

	public boolean stackIsFull(int i) {
		return getStack(i).isFull();
	}

	class Stack {

		int startPos;
		int length;
		
		Stack(int startPos, int length) {
			this.startPos = startPos;
			this.length   = length;
		}

		boolean isEmpty() {
			return length == 0;
		}

		boolean isFull() {
			return length == ThreeStacks.this.maxStackLength;
		}
	}

}