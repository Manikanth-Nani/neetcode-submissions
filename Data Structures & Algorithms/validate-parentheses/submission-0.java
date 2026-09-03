class Solution {
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
}
