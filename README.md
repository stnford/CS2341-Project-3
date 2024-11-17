This code uses javas buffer reader to read amazon-product-data.csv directly. On the eight line of the main class the file path is set up as src/amazon-product-data.csv so please run the program with the data file in the src folder. 



  In a red-black tree every node is either red or black, the root is always black, neither two consecutive red nodes or right leaning red nodes are allowed, and every path from leaf to root should contain the same number of black nodes. If all these are implemented correctly the longest path of red nodes should be no more than twice as long as the shortest path of black nodes.
  Insertion and deletion can result in some of these rules being violated. When this is the case the flipColors() funtion and roatateLeft() and roateRight() function will be called to adjust the tree structure until all the rules are satisfied again. 
  The efficenecy of the insertion and search operations of this program depend on the balance of the tree. If the tree satisfies all the rules of a RB tree efficient preformance with logarithmic time complexity is guarenteed. The time complexity of insertion and search in a red-black tree is O(log N), where N is the number of nodes. Both operations involve traversing from the root to a leaf along a path proportional to the height of the tree, which is guaranteed to be O(log N) due to balancing. Each operation makes sure that the height of the tree stays proportional to log(N). The self balancing nature of RB trees minimizes skewed growth and maintains its logorithmic height even with large data sets. This makes RB trees very scalable due to their ability to manage large datasets with little degradation in effecincy. For a dataset of 10,001 entries, the tree height would be approximately 2log2(10,001) which is approximately 26.


Program output when three elements are search for from the user and two insertions are hardcoded (one with a duplicate ID):

Enter product ID's seperated by ',' or type 'exit' to quit: 
3e19b6266cb28453080389529d5fbdf8,8a8338081f5e267402c5eb36f07d5bab,7bf79367acb42d2bde05d9ae8b9538a9

Search Results:

ID: 3e19b6266cb28453080389529d5fbdf8

    Name: Sterling Home Round Chair Side chest of drawers, Multicolor
    
    Category: Home & Kitchen | Furniture | Kids' Furniture | Nightstands
    
    Price: $440.26

ID: 8a8338081f5e267402c5eb36f07d5bab

    Name: Licenses Products Sublime Drunk Clown Sticker
    
    Category: Toys & Games | Arts & Crafts | Stickers
    
    Price: $4.59

ID: 7bf79367acb42d2bde05d9ae8b9538a9

    Name: Manhattan Toy Natural Historian Opposites Padded Cover Baby and Toddler Board Book
    
    Category: Toys & Games | Baby & Toddler Toys
    
    Price: $7.99

Insertions:

Inserted new product:

ID: dummyID

    Name: Wacky Gizmo
    
    Category: Cool | Awesome | Radical
    
    Price: $12,345.67

Error: Product with ID 2bb94aefc3467ed83860e0e2712d5f10 already exists.

Process finished with exit code 0
