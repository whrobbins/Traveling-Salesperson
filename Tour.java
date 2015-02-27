import java.util.*;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Travelling Salesperson Problem
 *
 */

public class Tour implements TourInterface
{
    // list of points in order of which we travel them
    private List<ListNode> nodeList;
    private int size;

    // instantiates the node list
    public Tour()
    {
        nodeList = new LinkedList<ListNode>();
        size = 0;
    }

    // return the number of points (nodes) in the list
    public int size()
    {
        return size;
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        nodeList.add(new ListNode(p));
        size++;
    }

    // print every node in the list
    public void print()
    {
        for(int i = 0; i < size; i++)   {
            System.out.println(nodeList.get(i).data);
        }
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {
        // Draw Points
        for(ListNode node : nodeList)   {
            g.fillOval((int)node.data.x - 2, (int)node.data.y - 2, 5, 5);
        }
        
        // Draw lines
        for(int i = 0; i < nodeList.size()-1; i++)    {
            // Delays to make it more visual
            // 4 second animation of the path
           try {
                Thread.sleep(5000 / size+1);//entire drawing takes five seconds regardless of # of nodes
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            
            ListNode fromPoint = nodeList.get(i);
            ListNode toPoint = nodeList.get(i+1);
            
            g.setColor(Color.getHSBColor((((float)i) / ((float)size)), 1.0f, 0.8f));
            g.drawLine((int)fromPoint.data.x, (int)fromPoint.data.y, 
                (int)toPoint.data.x, (int)toPoint.data.y);
        }
        // Draws path between last node and original node
        if(size > 0)
            g.drawLine((int)nodeList.get(0).data.x, (int)nodeList.get(0).data.y,
                (int)nodeList.get(size-1).data.x, (int)nodeList.get(size-1).data.y);
    }

    //calculate the distance of the Tour, by summing the distance between adjacent points
    public double distance()
    {
        double distance = 0;
        // Adds distance of the path
        for(int i = 0; i < size - 1; i++)   {
            distance += nodeList.get(i).data.distance(nodeList.get(i+1).data);
        }
        // Adds distance from last to first destination
        if(size > 0)
            distance += nodeList.get(size-1).data.distance(nodeList.get(0).data);

        return distance;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {
        ListNode newNode = new ListNode(p);

        //if the tour is empty, add the "home" point two times, once for the start, and once to account
        //  for the person's trip home
        if(size == 0)
        {
            nodeList.add(newNode);
            nodeList.add(newNode);
            size++;
            return;
        }

        // index of nearest node
        int nearest = 0;
        // the length of the distance between p and nearest
        double shortDistance = nodeList.get(0).data.distance(p);
        ListIterator<ListNode> iterator = nodeList.listIterator(0);
        
        // For each node in nodeList, check distance to p and keep track of index of nearest point
        while(iterator.hasNext())   {
            double thisDistance = iterator.next().data.distance(p);
            if(thisDistance < shortDistance)    {
                nearest = iterator.previousIndex();
                shortDistance = thisDistance;
            }
        }

        nodeList.add(nearest, newNode);
        size++;
        
        
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    {
        ListNode newNode = new ListNode(p);

        //if the tour is empty,add the "home" point two times, once for the start, and once to account
        //for the person's trip home
        if(size == 0)
        {
            nodeList.add(newNode);
            nodeList.add(newNode);
            size = 1;
            return;
        }

        // Distance not including p
        double originalDistance = 0;
        // Distance including p
        double newDistance = 0;
        // Smallest added distance over entire list
        double smallestIncrease = Double.MAX_VALUE;
        // Index right before the best insertion point
        int index = 0;

        ListIterator<ListNode> iterator = nodeList.listIterator(0); 
        for(int i = 0; i < size; i++)   {    
            originalDistance = iterator.next().data.distance(iterator.next().data);
            newDistance = iterator.previous().data.distance(p) + iterator.previous().data.distance(p);    

            iterator.next();   
            
            // Checks to see if the added distance from p is smaller than other distances
            if(newDistance - originalDistance < smallestIncrease)    {
                index = iterator.previousIndex();
                smallestIncrease = newDistance - originalDistance;
            }
            
        }
        
        nodeList.add(index+1, newNode);
        size++;
    }
    
    // Node interchange
    // Note: this modification is highly inefficient and is really more of an experiment (and has an annoying amount of comments...)
    public void interchange()
    {
        //gets two random indexes
        int indexOne = (int)(Math.random() * this.size);
        int indexTwo = (int)(Math.random() * this.size);
        
        //if either of the indexes are 0, return
        if(indexOne == 0 || indexTwo == 0)
            return;
        
        //indexOne is the index that occurs first in the list, indexTwo is the index that occurs second
        if(indexOne > indexTwo)
        {
            int temp = indexOne;
            indexOne = indexTwo;
            indexTwo = temp;
        }
        
        if(indexTwo - indexOne == 1)
             return;
            
        //gets the nodes at the two indexes
        //node one is the first node in the list, node two is the second node
        ListNode nodeOne = nodeList.get(indexOne);
        ListNode nodeTwo = nodeList.get(indexTwo);
        
        //sets up iterators at the specified indexes to easily iterate through the linked list
        ListIterator<ListNode> iteratorOne = nodeList.listIterator(indexOne - 1);
        ListIterator<ListNode> iteratorTwo = nodeList.listIterator(indexTwo - 1);
        
        
        // Checks the distance between the point before each node and the node plus the distance between each node and the node
        // after
        double originalDistanceOne = iteratorOne.next().data.distance(iteratorOne.next().data) + nodeOne.data.distance(iteratorOne.next().data);
        //iteratorOne is now at indexOne + 2
        double originalDistanceTwo = iteratorTwo.next().data.distance(iteratorTwo.next().data) + nodeTwo.data.distance(iteratorTwo.next().data);
        //iteratorTwo is now at indexTwo + 2
        
        //Checks what the distances would be if the two nodes were switched
        
        //gets distance between the point at indexOne + 1 and nodeTwo
        double newDistanceOne = iteratorOne.previous().data.distance(nodeTwo.data);
        //iteratorOne is moved to indexOne
        iteratorOne.previous();
        //adds distance between the point at indexOne - 1 and nodeTwo to newDistanceOne
        newDistanceOne += iteratorOne.previous().data.distance(nodeTwo.data);
        
        //gets distance between the point at indexTwo + 1 and nodeOne
        double newDistanceTwo = iteratorTwo.previous().data.distance(nodeOne.data);
        //iteratorTwo is moved to indexTwo
        iteratorTwo.previous();
        //adds distance between the point at indexTwo - 1 and nodeOne to newDistanceTwo
        newDistanceTwo += iteratorTwo.previous().data.distance(nodeOne.data);
            
        // Checks to see if the sum of the newDistances is smaller than the sums of the originalDistances
        if((newDistanceOne + newDistanceTwo) < (originalDistanceOne + originalDistanceTwo))  
        {
            //switch the two nodes
            nodeList.remove(nodeOne);
            nodeList.remove(nodeTwo);
            nodeList.add(indexOne, nodeTwo);
            nodeList.add(indexTwo, nodeOne);
        }
    }
    
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }
    }
}