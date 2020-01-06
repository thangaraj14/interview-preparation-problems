package geeksforgeeks;

class Trie {
    
    class TrieNode{
        char data;
        TrieNode[] children;
        boolean isWord;
        TrieNode(char data){
            this.data=data;
            this.children= new TrieNode[26];
        }
    }

    
    TrieNode root;
    public Trie() {
        this.root=new TrieNode(' ');
    }
    
  
    public void insert(String word) {
        TrieNode head=root;
        
        for(int i=0;i<word.length();i++){
            char temp= word.charAt(i);
            if(head.children[temp-'a']==null){
                head.children[temp-'a']= new TrieNode(temp);
                
            }
            head= head.children[temp-'a'];
        }
        head.isWord=true;
    }
    
   
    public boolean search(String word) {
       TrieNode result= helperFn(word);
        if(result!=null && result.isWord) return true;
        return false;
    }
    
 
    public boolean startsWith(String prefix) {
        return helperFn(prefix)!=null;
    }
    
    public TrieNode helperFn(String data){
        TrieNode head=root;
        for(int i=0;i<data.length() && head!=null;i++){
             char temp= data.charAt(i);
             head= head.children[temp-'a'];
        }
        return head;
    }
    
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */