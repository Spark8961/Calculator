public class Calculation {

    private String rawExpression; 
    private String[] tokenizedExpression;
    private String[] postfixExpression;

    public Calculation(String rawExpression){

        this.rawExpression = rawExpression;
        prepareExpression();
    }

    public String evaluate(){

        Stack stack = new Stack(postfixExpression.length);

        for(int i = 0; i < postfixExpression.length; i++){

            if(!isOperator(postfixExpression[i])){

                stack.push(postfixExpression[i]);
            }else{

                float b = Float.parseFloat(stack.pop());
                float a = Float.parseFloat(stack.pop());

                switch(postfixExpression[i]){

                    case "+": stack.push(Float.toString(a + b));
                    break;
                    case "-": stack.push(Float.toString(a - b));
                    break;
                    case "*": stack.push(Float.toString(a * b));
                    break;
                    case "/":
                    if(b == 0){

                        throw new ArithmeticException("Division by 0");
                    }else{

                        stack.push(Float.toString(a / b));
                    }
                    break;
                    default:
                    break;
                }
            }
        }
        return stack.peek();
    }

    private void prepareExpression(){

        tokenizedExpression = tokenize(rawExpression);
        postfixExpression = toPostfix(tokenizedExpression);
    }

    private String[] tokenize(String re) {

        String[] operands = re.split("[\\+\\-\\*\\/]");
        String[] operators = re.split("\\d+(\\.\\d+)?");
        String[] temp = new String[operators.length + operands.length];
        String[] tokens = new String[temp.length - 1];
        
        for(int i = 0; i < temp.length; i++){

            if(i % 2 == 0){

                temp[i] = operators[i / 2];
            }else{

                temp[i] = operands[i / 2];
            }   
        }

        System.arraycopy(temp, 1, tokens, 0, temp.length - 1);
        
        if(tokens.length % 2 == 1){

            return tokens;
        }else{

            throw new ArithmeticException("Invalid expression");
        }    
    }

    private String[] toPostfix(String[] te){

        Stack stack = new Stack(te.length);
        Stack temp = new Stack(te.length); 
        String[] postfix = new String[te.length];

        for(int i = 0; i < te.length; i++){

            if(!isOperator(te[i])){

                temp.push(te[i]);
            }else{

                while(!stack.isEmpty() && (stack.peek() != null) && precedence(te[i]) <= precedence(stack.peek())){

                    temp.push(stack.pop());

                }
                stack.push(te[i]);             
            }  
        }
        while(!stack.isEmpty()){

            temp.push(stack.pop());                                
        }

        postfix = temp.getStack();
        return postfix;
    }

    private static boolean isOperator(String str){

        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private static int precedence(String str){

        switch(str){

            case "*" :
            case "/" :
            return 2;

            case "+" :
            case "-" :
            return 1;

            default :
            return -1;
        }
    }
}
