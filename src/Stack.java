public class Stack {

    private String[] stack;
    private int top = -1;
    
    public Stack(int size){

        stack = new String[size];
    }

    public void push(String data){

        if(isFull()){

            throw new ArrayIndexOutOfBoundsException("Stack is full");
        }else{

            top++;
            stack[top] = data;
        }
    }

    public String pop(){

        if(isEmpty()){

            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        }else{

            String data = stack[top];
            stack[top] = null;
            top--;
            return data;
        }
    }

    public String peek(){

        if(!isEmpty()){

            return stack[top];
        }else{

            return null;
        } 
    }

    public String[] getStack(){

        return stack;
    }

    public boolean isEmpty(){

        return top == -1;
    }

    private boolean isFull(){

        return top == stack.length - 1;
    }
}
