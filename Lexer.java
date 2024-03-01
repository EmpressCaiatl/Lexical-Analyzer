import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    private final String source;                             //takes the source file and appends text to this string
    private final List<Token> tokens = new ArrayList<>();   //Array of tokens
    private int start = 0;                                  //marker for string traversal
    private int current = 0;                                //marker for string traversal
    private int line = 1;                                   //lines for errors

    public static int x;                                    //counter variable for category

    Lexer(String source){
        this.source = source;
    }          //constructor
    private static final Map<TokenType, Integer> category;        //hashmap for tokens to category
    private static final Map<String, TokenType> keywords;   //hashmap for tokens to keywords

    /* Sets the keywords and category as static values
    using TokenType enums and x */
    static {
        keywords = new HashMap<>();
        category = new HashMap<>();

        keywords.put("output", TokenType.OUTPUT);
        category.put(TokenType.OUTPUT, x);
        x++;
        keywords.put("on",  TokenType.ON);
        category.put(TokenType.ON, x);
        x++;
        keywords.put("create", TokenType.CREATE);
        category.put(TokenType.CREATE, x);
        x++;
        keywords.put("constant",  TokenType.CONSTANT);
        category.put(TokenType.CONSTANT, x);
        x++;
        keywords.put("elseif", TokenType.ELSE_IF);
        category.put(TokenType.ELSE_IF, x);
        x++;
        keywords.put("me", TokenType.ME);
        category.put(TokenType.ME, x);
        x++;
        keywords.put("until", TokenType.UNTIL);
        category.put(TokenType.UNTIL, x);
        x++;
        keywords.put("public", TokenType.PUBLIC);
        category.put(TokenType.PUBLIC, x);
        x++;
        keywords.put("private", TokenType.PRIVATE);
        category.put(TokenType.PRIVATE, x);
        x++;
        keywords.put("alert",  TokenType.ALERT);
        category.put(TokenType.ALERT, x);
        x++;
        keywords.put("detect", TokenType.DETECT);
        category.put(TokenType.DETECT, x);
        x++;
        keywords.put("always", TokenType.ALWAYS);
        category.put(TokenType.ALWAYS, x);
        x++;
        keywords.put("check", TokenType.CHECK);
        category.put(TokenType.CHECK, x);
        x++;
        keywords.put("parent", TokenType.PARENT);
        category.put(TokenType.PARENT, x);
        x++;
        keywords.put("blueprint", TokenType.BLUEPRINT);
        category.put(TokenType.BLUEPRINT, x);
        x++;
        keywords.put("system",TokenType.NATIVE);
        category.put(TokenType.NATIVE, x);
        x++;
        keywords.put("is", TokenType.INHERITS);
        category.put(TokenType.INHERITS, x);
        x++;
        keywords.put("cast", TokenType.CAST);
        category.put(TokenType.CAST, x);
        x++;
        keywords.put("say", TokenType.SAY);
        category.put(TokenType.SAY, x);
        x++;
        keywords.put("now", TokenType.NOW);
        category.put(TokenType.NOW, x);
        x++;
        keywords.put("while", TokenType.WHILE);
        category.put(TokenType.WHILE, x);
        x++;
        keywords.put("package", TokenType.PACKAGE);
        category.put(TokenType.PACKAGE, x);
        x++;
        keywords.put("times", TokenType.TIMES);
        category.put(TokenType.TIMES, x);
        x++;
        keywords.put("repeat", TokenType.REPEAT);
        category.put(TokenType.REPEAT, x);
        x++;
        keywords.put("else", TokenType.ELSE);
        category.put(TokenType.ELSE, x);
        x++;
        keywords.put("returns", TokenType.RETURNS);
        category.put(TokenType.RETURNS, x);
        x++;
        keywords.put("return", TokenType.RETURN);
        category.put(TokenType.RETURN, x);
        x++;
        keywords.put("and", TokenType.AND);
        category.put(TokenType.AND, x);
        x++;
        keywords.put("input", TokenType.INPUT);
        category.put(TokenType.INPUT, x);
        x++;
        keywords.put("undefined", TokenType.NULL);
        category.put(TokenType.NULL, x);
        x++;
        keywords.put("shared", TokenType.STATIC);
        category.put(TokenType.STATIC, x);
        x++;
        keywords.put("action", TokenType.ACTION);
        category.put(TokenType.ACTION, x);
        x++;
        keywords.put("integer", TokenType.INTEGER_KEYWORD);
        category.put(TokenType.INTEGER_KEYWORD, x);
        x++;
        keywords.put("number", TokenType.NUMBER_KEYWORD);
        category.put(TokenType.NUMBER_KEYWORD, x);
        x++;
        keywords.put("text", TokenType.TEXT);
        category.put(TokenType.TEXT, x);
        x++;
        keywords.put("boolean", TokenType.BOOLEAN_KEYWORD);
        category.put(TokenType.BOOLEAN_KEYWORD, x);
        x++;
        keywords.put("use", TokenType.USE);
        category.put(TokenType.USE, x);
        x++;

        keywords.put("not", TokenType.NOT);
        category.put(TokenType.NOT, x);
        keywords.put("Not", TokenType.NOT);
        category.put(TokenType.NOT, x);
        x++;

        keywords.put("not=", TokenType.NOTEQUAL);
        category.put(TokenType.NOTEQUAL, x);
        x++;
        keywords.put("Not=", TokenType.NOTEQUAL);
        category.put(TokenType.NOTEQUAL, x);
        x++;
        keywords.put("if", TokenType.IF);
        category.put(TokenType.IF, x);
        x++;
        keywords.put("end", TokenType.END);
        category.put(TokenType.END, x);
        x++;
        keywords.put("class", TokenType.CLASS);
        category.put(TokenType.CLASS, x);
        x++;
        keywords.put("or", TokenType.OR);
        category.put(TokenType.OR, x);
        x++;
        keywords.put("mod", TokenType.MODULO);
        category.put(TokenType.MODULO, x);
        x++;

        keywords.put("true", TokenType.BOOLEAN);
        keywords.put("false", TokenType.BOOLEAN);
        category.put(TokenType.BOOLEAN, x);
        x++;
    }

    //Begins the scanning process
    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            //beginning of the next lexeme
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    //the brain
    //in charge of scanning and determining tokens
    //uses helper functions
    private void scanToken() {
        char c = advance();
        //reads char by char and checks each char
        switch (c) {
            case '(':
                x++;
                category.put(TokenType.LEFT_PAREN,x);
                addToken(TokenType.LEFT_PAREN);
                x++;
                break;
            case ')':
                x++;
                category.put(TokenType.RIGHT_PAREN,x);
                addToken(TokenType.RIGHT_PAREN);
                x++;
                break;
            case '[':
                category.put(TokenType.LEFT_SQR_BRACE,x);
                addToken(TokenType.LEFT_SQR_BRACE);
                x++;
                break;
            case ']':
                category.put(TokenType.RIGHT_SQR_BRACE,x);
                addToken(TokenType.RIGHT_SQR_BRACE);
                x++;
                break;
            case ',':
                category.put(TokenType.COMMA,x);
                addToken(TokenType.COMMA);
                x++;
                break;
            case '.':
                category.put(TokenType.PERIOD,x);
                addToken(TokenType.PERIOD);
                x++;
                break;
            case '-':
                category.put(TokenType.MINUS,x);
                addToken(TokenType.MINUS);
                x++;
                break;
            case '+':
                category.put(TokenType.PLUS,x);
                addToken(TokenType.PLUS);
                x++;
                break;
            case '/':
                category.put(TokenType.COMMENTS,x);
                x++;
                category.put(TokenType.DIVIDE,x);
                x++;

                //check for comments
                if(match('/')){
                    advance(); //consume the '/'
                    while(peek() != '\n' && !isAtEnd()){advance();}
                    addToken(TokenType.COMMENTS);
                } else if (match('*')) {
                    advance(); //consume the '*'

                    //loop until as long as the "*/" does not end the comment
                    //and no EOF
                    while((peek() != '*' || peekNext() != '/') && !isAtEnd()) {
                        //check for end of string
                        //error avoidance
                        //continue consuming until we reach "*/"
                        advance();
                    }
                    //check for EOF
                    if(isAtEnd()){ //error output
                        String temp = source.substring(start,current);
                        System.out.println("Error: '"+ temp + "' is not a complete comment. Line:" + line);
                    }else { //consume the final "*/" and add comment token
                        advance();
                        advance();
                        addToken(TokenType.COMMENTS);
                    }
                }else{ //if no match, then this is just divider
                    addToken(TokenType.DIVIDE);
                }
                break;
            case '*':
                category.put(TokenType.MULTIPLY,x);
                addToken(TokenType.MULTIPLY);
                x++;
                break;
            case ':':
                category.put(TokenType.COLON,x);
                addToken(TokenType.COLON);
                x++;
                break;
            case '"':
                if(isAlpha(peekNext())){
                    string();
                    x++;
                    break;
                }else{
                    category.put(TokenType.DOUBLE_QUOTE,x);
                    x++;
                    addToken(TokenType.DOUBLE_QUOTE);}
                break;
            case '=':
                category.put(TokenType.EQUALITY,x);
                addToken(TokenType.EQUALITY);
                x++;
                break;
            case '<':
                category.put(TokenType.LESS,x);
                x++;
                category.put(TokenType.LESS_EQUAL,x);
                x++;
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                category.put(TokenType.GREATER,x);
                x++;
                category.put(TokenType.GREATER_EQUAL,x);
                x++;
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                break;
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            case '\n':
                line++;
                break;

            default:
                if (isDigit(c)) {
                    x++;
                    number();
                } else if (isAlpha(c)) {
                    x++;
                    identifier();
                }else {
                    System.out.println("Error: '"+ c + " 'is not allowed. Line:" + line);
                }
                break;
        }
    }

    //helper fxn that checks if the first lexeme is an identifier
    //also checks for some edge cases like N/not=
    private void identifier() {

        while (isAlphaNumeric(peek())){ advance();}

        String temp = source.substring(start,current);
        //hard check for N/not=
        if((temp.equals("not")||temp.equals("Not")) && peek() == '='){
            advance(); //consume the =
            x++;
            category.put(TokenType.NOTEQUAL, x);
            addToken(TokenType.NOTEQUAL);
        }else {
            TokenType type = keywords.get(temp);
            //if no keyword found, then it is ID
            if (type == null) {
                type = TokenType.ID;
                x++;
                category.put(TokenType.ID, x);
            }
            //adds keyword token
            x++;
            addToken(type);
        }
    }

    //helper fxn that determines if lexeme is of a number type
    //differentiates between decimal and number
    //checks for edge cases with letters
    private void number() {
        while (isDigit(peek())) advance();
        // Look for a decimal
        if (peek() == '.' && isDigit(peekNext())) {
            advance(); //consume the decimal
            while (isDigit(peek())){advance();}
            x++;
            category.put(TokenType.DECIMAL, x);
            addToken(TokenType.DECIMAL, Double.parseDouble(source.substring(start,current)));
        }else{ //else, it is a number without a decimal and can be an INT

            //must check for error with letter
            if(isAlpha(peek())){
                while(isAlpha(peek())){ advance();} //check for more letters
                while(isDigit(peek())){ advance();} //check for more numbers
                //stops at whitespace
                String temp = source.substring(start,current); //gets the errored string
                System.out.println("Error: '"+ temp + "'is not allowed. Line:" + line); //produces error

            }else { //otherwise keep proceeding with Int
                while (isDigit(peek())) {
                    advance();
                }
                x++;
                category.put(TokenType.INT, x);
                addToken(TokenType.INT);
            }
        }
    }

    //helper fxn to read string and determine if string terminates correctly
    //errors out if string is unterminated
    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        //check for bad string
        if (isAtEnd()) {
            System.out.println("Error: Unterminated string at line " + line);
            return;
        }

        // The closing " to the string
        advance();

        // Trim quotes
        String value = source.substring(start + 1, current - 1);
        category.put(TokenType.STRING, x);
        addToken(TokenType.STRING, value);
    }

    //helper fxn that basically checks for specified char at the next
    //position in the string
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    //helper fxn that allows read ahead 1 space
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    //helper fxn that allows read ahead 2 spaces
    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    //helper fxn that checks if a char is alphabetical
    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    //helper fxn that combines isAlpha and isDigit
    //helps with conditionals
    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    //helper fxn that checks if char is numerical
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    //helper fxn that ensures reading string does not exceed bounds
    private boolean isAtEnd() {
        return current >= source.length();
    }

    //helper fxn that consumes a char from source string
    private char advance() {
        return source.charAt(current++);
    }

    //helper fxn that takes a lexeme and makes a token
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    //helper fxn that allows edge cases
    // with decimals, strings, and comments to be tokenized
    // also outputs the category, token type, and value to the console
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
        System.out.println("Token Category: " + category.get(type) +
                ", " + type + " keyword, value \"" + text + "\"");
    }


}

