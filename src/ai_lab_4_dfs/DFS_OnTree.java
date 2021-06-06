/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_lab_4_dfs;

import java.util.*;

// Node class consist of 3 members left child , Data and right child
class Node
{ 
    Integer data;
    Node lchild;
    Node rchild;
    Node()
    {
        this.lchild=null;
        this.rchild=null;
        
    }
}
/*
    This class is used for returning multiple values from function
    such as while inserting and deleting data from tree find function is used for
    finding the location and its parent location, so for returning Parent and location from
    find function obj of this class will be used as object of this class will contain two values
    parent and location
*/
class parloc
{
    Node parent;
    Node location;
    parloc()
    {
     this.parent=null;
     this.location=null;
    }
}

/*  
    This class is used for node implementation which is used to perform these task
    1-> Insert data in tree
    2-> Delete data from tree
    3-> Triverse tree using inoder
    4-> apply DFS on that tree.
*/
class NodeI
{
    Node root;
    NodeI()
    {
        this.root=null;
    }
    protected parloc find(int d,parloc obj)
    {
        Node ptr,ptrsave;
        if(this.root==null)
        {
            obj.location=null;
            obj.parent=null;
            return obj;
        }
        if(this.root.data==d)
        {
            obj.location=root;
            obj.parent=null;
            return obj;
        }
        if(this.root.data<d)
        {
            ptr=this.root.rchild;
        }
        else
        {
            ptr=this.root.lchild;
        }
        ptrsave=root;
        while(ptr!=null)
        {
            if(ptr.data==d)
            {
                obj.location=ptr;
                obj.parent=ptrsave;
                return obj;
            }
            else if(ptr.data<d)
            {
                  ptrsave=ptr;
                  ptr=ptr.rchild;
            }
            else
            {
                ptrsave=ptr;
                ptr=ptr.lchild;
                
            }
        }
        obj.location=null;
        obj.parent=ptrsave;
        return obj;
    }
    public void insert(int d)
    {
        parloc obj=new parloc();
        obj=this.find(d,obj);
        if(obj.location!=null)
        {
            System.out.println("This data is already Exist in Tree");
            return;
        }
        System.out.println("***Ok we fund Got location Now inserting data***");
        Node tmp=new Node();
        tmp.data=d;
        if(obj.parent==null)
        {
            this.root=tmp;
            System.out.println("**Data has been inserted in the root**");
        }
        else
        {
            if(d<obj.parent.data)
            {
                obj.parent.lchild=tmp;
                System.out.println("**Data has been inserted in the left child of : "+obj.parent.data+" **\n");
            }
            else
            {
                obj.parent.rchild=tmp;
               
                System.out.println("**Data has been inserted in the right child of : "+obj.parent.data+" **\n");
            }
        }
        
    }
    public void del(int d)
    {
        parloc obj=new parloc();
        if(this.root==null)
        {
            System.out.println("Tree is Empty");
            return;
        }
        obj=this.find(d,obj);
        if(obj.location==null)
        {
            System.out.println("Data is not in the Tree");
            return;
        }
        if(obj.location.lchild==null&&obj.location.rchild==null)
        {
            case_a(obj);
        }
        if(obj.location.lchild!=null&&obj.location.rchild==null)
        {
            case_b(obj);
        } 
        if(obj.location.lchild==null&&obj.location.rchild!=null)
        {
            case_b(obj);
        }
        if(obj.location.lchild!=null && obj.location.rchild!=null)
        {
            case_c(obj);
        }
    }
    protected void case_a(parloc obj)
    {
        if(obj.parent==null)
        {
            this.root=null;
        }
        else
        {
            if(obj.parent.lchild==obj.location)
            {
                System.out.println("**This data has been deleted : "+obj.parent.lchild.data);
                obj.parent.lchild=null;
            }
            else if(obj.parent.rchild==obj.location)
            {
                System.out.println("this data has been deleted : "+obj.parent.rchild.data);
                obj.parent.rchild=null;
            }
        }
    }
    protected void case_b(parloc obj)
    {
        Node child;
        if(obj.location.lchild!=null)
        {
            child=obj.location.lchild;
        }
        else
        {
            child=obj.location.rchild;
        }
        if(obj.parent==null)
        {
            System.out.println("this data is deleted from root "+obj.location.data);
            this.root=child;
        }
        else
        {
            if(obj.parent.lchild==obj.location)
            {
                System.out.println("this data is deleted from root "+obj.location.data);
                obj.parent.lchild=child;
            }
            else
            {
                System.out.println("this data is deleted from root "+obj.location.data);
                obj.parent.rchild=child;
            }
        }
        
    }
    protected void case_c(parloc obj)
    {
        parloc objtemp=new parloc();
        Node suc,pasuc,ptr,ptrsave;
        ptr=obj.location.rchild;
        ptrsave=obj.location;
        while(ptr.lchild!=null)
        {
            ptrsave=ptr;
            ptr=ptr.lchild;
        }
        suc=ptr;
        pasuc=ptrsave;
        objtemp.location=suc;
        objtemp.parent=pasuc;
        //obj.location=suc;
        //obj.parent=pasuc;
        if(suc.lchild==null&&suc.rchild==null)
        {
            System.out.println("loc : "+obj.location.data+" par : "+obj.parent.data);
            case_a(objtemp);
        }
        else
        {
            case_b(objtemp);
        }
        if(obj.parent==null)
        {
            this.root=suc;
        }
        else
        {
            if(obj.location==obj.parent.lchild)
            {
                obj.parent.lchild=suc;
            }
            else
            {
                obj.parent.rchild=suc;
            }
        }
        suc.lchild=obj.location.lchild;
        suc.rchild=obj.location.rchild;
    }
    public void inorder(Node ptr)
    {
        if(root==null)
        {
            System.out.println("Tree is Empty");
        }
        if(ptr!=null)
        {
            inorder(ptr.lchild);
            System.out.print(ptr.data+" ");
            inorder(ptr.rchild);
        }
    }
    public void DFS(int search_data)
    {
        Stack<Node> stack = new Stack<>();
        List<Integer> visitied_vertices;
        visitied_vertices = new ArrayList<>();
        //int visitied_vertices[] = new int[30];
        //int size_visited = 0;
        Node move = this.root;
        
        stack.add(move);
        visitied_vertices.add(move.data);
        
        while (!stack.isEmpty())
        {
            Node top_of_stack = stack.peek();
            
            if(top_of_stack.data == search_data)
            {
                System.out.println("\n"+top_of_stack.data);
                System.out.println("::::::: -> Congrats Your Data has been Found in the Tree <- :::::::");
                System.out.println("Visited Nodes : "+visitied_vertices);
                return;
            }
            else if(top_of_stack.lchild != null && !visitied_vertices.contains(top_of_stack.lchild.data))
            {
                stack.add(top_of_stack.lchild);
                visitied_vertices.add(top_of_stack.lchild.data);
                
            }
            else if(top_of_stack.rchild != null && !visitied_vertices.contains(top_of_stack.rchild.data))
            {
                stack.add(top_of_stack.rchild);
                visitied_vertices.add(top_of_stack.rchild.data);
            }
            else
            {
                System.out.print("*********** Poped ***********");
                System.out.println(" : "+stack.pop().data);
            }
        }
        System.out.println("::::::: -> Sorry Your Data is not Found in the Tree <- :::::::");
        System.out.println("Visited Nodes : "+visitied_vertices);
    }
}

public class DFS_OnTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("****************   Welcome to DFS   ************************");
        System.out.println("****************  Build Tree First  ************************");
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        int cho,val;NodeI obj1=new NodeI();
        while(true)
        {
          System.out.println("\n-> press 1 for insert");
          System.out.println("-> press 2 for delete");
          System.out.println("-> press 3 for inorder");
          System.out.println("-> press 4 for DFS");
          System.out.println("-> press 5 for Exit : ");
        cho=input.nextInt();
        switch(cho)
        {
            case 1:
            {
                System.out.print("Enter a number : ");
                val=input.nextInt();
                obj1.insert(val);
                break;
            }
            case 2:
            {
                System.out.print("Enter a number : ");
                val=input.nextInt();
                obj1.del(val);
                break;
            }
            case 3:
            {
               obj1.inorder(obj1.root);
               break;
            }
            case 4:
            {
                System.out.print("Enter a number : ");
                val=input.nextInt();
                obj1.DFS(val);
                break;
            }
            default:
            {
                System.out.println("************* -> Program Has been Ended <- ***********************");
                return;
            }
        }  
        }
        
        
    }
    
    
}
