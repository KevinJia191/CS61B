/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

/**
 *
 * @author Kevin.Jia
 */
public class LockDList extends DList{
    public void lockNode(DListNode node){
        ((LockDListNode)node).lock = true;
    }
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }
    
    public void remove(DListNode node){
        if(((LockDListNode)node).lock){
            
        }
        else{
            super.remove(node);
        }
        
    }
    
}
