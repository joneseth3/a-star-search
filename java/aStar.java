package aStar;

import java.lang.*;
import java.util.*;


public class aStar 
{
    
    public static int row = 15;
    public static int col = 15;
    public static Node [][] node = new Node[row][col]; 
    public static int [][] board = new int [row][col]; 
    public static Node start; 
    public static Node end;

    public static void main(String[] args) 
    { 
        int programContinue = 1;

        while(programContinue == 1)
        {


            System.out.println("\n READ ME BELOW! \n \nThe 0's are pathable nodes and the 1's are unpathable nodes. " + 
                "The path will be shown on the graph at the end when it is found. The 0's will be replaced with 4's to show the path." + 
                "The goal node will be denoted as a 3." + 
                "The options to enter nodes are 0-14 on both [x,y]. So for example if I wanted" +
                " the 15th node to be by goal I would put 14, just like an array where 0 is 1, 1 is 2, 2 is 3, ect. " );

            aStar_methods state = new aStar_methods();
            state.setBlockedNodes(board);
            state.writeToNode(board, node);

            state.nodeGraph(node);


            start = state.getStart(node);
            end = state.getEnd(node);


            state.getHeuristic(node, end);

            state.displayHeuristic(node);

            start.setF();
            start.setG(0);
            start.setH(node[start.getRow()][start.getCol()].getH());


            Node[][] copy = new Node[row][col];

            for (int i = 0; i < node.length; i++)
            {
                for (int j = 0;j < node[0].length; j++)
                {
                    copy[i][j] = node[i][j];
                }
            }

            boolean search = true;

    
            ArrayList<Node> openList = new ArrayList<>();
            ArrayList<Node> closeList = new ArrayList<>();

            openList.add(start);

            while(search)
            {
                Node n = openList.remove(0);


                if(n.equals(end))
                {
                    System.out.println("\nEnd node path has been found.");
                    System.out.println("\nThe path is: ");
                    search = false;

                    while(!n.equals(start))
                    {
                        System.out.print(n.getParent().toString()); 
                        n = n.getParent();

                        copy[n.getRow()][n.getCol()].setType(4); //the 4 was 8
                    }
                }else{
                    
                    int nextCol = n.getCol();
                    int nextRow = n.getRow();

                    for (int i = (nextRow - 1); i <= (nextRow + 1); i++)
                    {
                        for (int j = (nextCol - 1); j <= (nextCol + 1); j++)
                        {
                            
                            if((i >= 0 && i < row) && (j >= 0 && j < row) && (i != nextRow || j != nextCol) && (node[i][j].getType() != 1))                        {
                                Node newNode = new Node(i, j, 0);
                                System.out.println("Getting node: " + newNode.toString());
                                newNode.setParent(n);

                                int updateG = 10;

                                if(Math.abs(i - nextRow) + Math.abs(j - nextCol) == 2)
                                {

                                    updateG = 14;
                                
                                }

                                newNode.setG(n.getG() + updateG);
                                newNode.setH(node[start.getRow()][start.getCol()].getH());
                                newNode.setF();

                                if(state.checkinList(newNode, closeList) == null){
                                    Node k = state.checkinList(newNode, openList);
                                    if(k == null)
                                    {
                                        openList.add(newNode);
                                        System.out.println("\nAdding Node: " + newNode.toString()+" to heap\n");

                                    }else{

                                        if(newNode.getG() < k.getG())
                                        {
                                            k.setG(newNode.getG());
                                            k.setParent(n);

                                        }
                                    }
                                }
                            }
                        }
                    }
                    state.sort(openList); closeList.add(n);
                }
            }
            state.nodeGraph(copy);

            Scanner scanner = new Scanner(System.in);
            System.out.println("If you want to chose another node, press 1 but if you want the program to finish press 0.");
            programContinue = scanner.nextInt();

        }//end of while
    }//end of main 
}//end of astar class
