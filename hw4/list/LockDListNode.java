/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

/**
 *
 * @author Kevin.Jia
 */
public class LockDListNode extends DListNode{
    public boolean lock;
    
    public LockDListNode(Object i, DListNode p, DListNode n){
        super(i,p,n);
        lock = false;
    }
}
