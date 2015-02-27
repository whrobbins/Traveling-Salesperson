PROJECT TITLE: Traveling Salesperson Problem
PURPOSE OF PROJECT: Implement two greedy heuristics in the attempt to find
an optimal solution to the Traveling Salesperson Problem
DATE: Sept 22, 2014
AUTHOR:  Will Robbins, Kaitlyn Lee

==============
Bonus Material
==============
    Progressively drawing path:
        To visually show the path of the salesperson, we simply told the thread to sleep in the graphics method.  
        To ensure that a 1000 node path doen't take forever to render, the thread sleeps for
        (5/nodeSize) seconds. No matter the size of the path, it will always take 5 seconds to draw.
    
    Chromatic path representation:
        It is much easier to see the tour progress when the path is coloured.  As it draws, the
        connecting lines cycle from red, all the way through the rainbow, and ultimately back to red.
        The graphics object can easily be changed to draw with a different color line.  For each
        point in the tour, we simply changed the color to (i/360) where i is the point
        that we're colouring.  The Java Color class has a very handy static function which accepts
        a parameter as a decimal, which is then translated into RGB values.
        
    JButtons for selecting algorithms/data
        We realized that commenting/uncommenting is a huge hassle.  After adding in a couple of JFrames
        with JButtons, you can select whichever algorithm or tour you want to run.  To implement this,
        we created two new classes, one for controlling the algorithm chooser, and one for controlling
        the map chooser.  In the driver class, we simply replaced the string that would refer to
        the map with a variable that gets assigned based on the button input.  We also created a new
        int variable in the main method, which gets a value from the algorithm button.
        From there, it was just a matter of an if/else statement to run a specific heuristic and 
        start the timer.

======================
Algorithm Descriptions 
======================
    insertNearest()
        First, a new node is created with the parameter Point p as data. If the tour is empty, the 
        node is added to the list and points back to itself to make the trip a loop. If the tour
        is not empty, then two variables are delcared. The integer nearest is the index of the nearest
        node. Double shortDistance is the length between p and the point at index nearest. For each
        node n in nodeList, the distance between the point that n holds and p is compared to shortDistance.
        If the distance is shorter than shortDistance, the index of n is recorded at index and
        shortDistance is updated to the distance between n and p. In order to traverse the list, we used
        a ListIterator. These are objects that are built into each LinkedList. It is essentially a pointer
        to a certain object in a list. While there was another node in the list, we moved the iterator one 
        index further.This allowed us to traverse the list without having to loop through it multiple 
        times, helping to reduce the complexity of the algorithm. Once we had gone through each point,
        p is inserted at the index right after index (or right after the point it is closest to). Size
        is incremented to reflect the additional point. This method has a Big O complexity of N because
        we used an iterator to go through the list. Since we are only moving the iterator rather than 
        iterating through the list multiple times, we only have to access each element in the list once, 
        resulting in the N complexity. However, since we are running this method multiple times (once for
        each point), the total complexity for the algorithm is N^2.
        
    insertSmallest()
        First, a new node is created with the parameter Point p as data. If the tour is empty, the
        node is added to the list and points back to itself to make the trip a loop. If the tour is
        not empty, then four variables are delcared. Double originalDistance is the distance between
        two consecutive points. Double newDistance is the distance between the same two points if p is
        inserted between them. Double smallestIncrease is the smallest recorded value of newDistance.
        Integer index is the index of the node before the best insertion point. Then, an iterator is 
        created. Iterators, as previously mentioned, allow us to traverse a list efficiently. For each
        of the nodes in our list, we got the distance between that point and the next point and compared
        it to the distance if we were to add point p in between. If the difference between those two 
        distances are less than smallest increase, index is set to the index is set to i and smallestIncrease
        is set to the difference. We then add the point to index+1 and increment size. This method has a Big O complexity of N because
        we used an iterator to go through the list. Since we are only moving the iterator rather than 
        iterating through the list multiple times, we only have to access each element in the list once, 
        resulting in the N complexity. However, since we are running this method multiple times (once for
        each point), the total complexity for the algorithm is N^2.
        
    interchange()
        An arbitrary number of times, we find two random points in the tour (the first at indexOne
        and the second at indexTwo). Using iterators, we got the original distance (originalDistanceOne
        and originalDistanceTwo) and what the distance would be if we switched the two points 
        (newDistanceOne and newDistanceTwo). If the sums of the new distances is less than the 
        sum of the current distances, we switch the two nodes. This method has a complexity of N because 
        we must iterate through the list to find the desired points at the generated index. We realized
        that the method does not work when either of the indecies are 0 or when the points are right
        next to each other, so we stopped the method if this was true before it could execute.
        
        