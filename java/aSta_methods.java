package aStar;

import java.util.*;
import java.lang.*;



public class aStar_methods
{

    //shows the node graph
    public void nodeGraph(Node [][]d) 
    {

        System.out.println("\nThis is the node graph.");

        for(int i = 0;i < d.length; i++) 
        {
            System.out.print("\n");


            for(int j = 0;j < d[0].length; j++) {
                System.out.print(d[i][j].getType()+ " ");
                System.out.print(" ");
            }
        }
        System.out.println("\n");
    }



    public void setBlockedNodes(int [][]b) 
    {

        Random rand = new Random();
        for(int i = 0; i < b.length;i++ )
        {
            for(int j = 0;j < b[0].length;j++) 
            {

                float chance = rand.nextFloat();
                if (chance <= 0.10f){
                   
                    b[i][j]=1;
                }
            }
        }
    }


    public void writeToNode(int [][] a, Node [][]b)
    {
        for (int i = 0; i < a.length;i++)
        {
            for (int j=0; j<a[0].length;j++)
            {
                b[i][j] = new Node(i, j, a[i][j]);
            }
        }
    }



    
    public Node getStart(Node[][]b) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your starting node cordinates, please put a space inbetween the two numbers.");
        int startCol = scanner.nextInt();
        int startRow = scanner.nextInt();


        b[startRow][startCol].setType(2);


        Node s = new Node(startRow, startCol, 2);
        s.setG(0);
        s.setF();
        return s;

    }

    public Node getEnd(Node [][]b)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your end node, the same way as you entered the start node.");
        int col = scanner.nextInt();
        int row = scanner.nextInt();

        b[row][col].setType(3);


        Node g = new Node(row, col, 3);
        g.setF();


        return g;

    }
     public static void getHeuristic(Node [][]b, Node g)
     {
         for (int i = 0;i < b.length; i++)
         {
             for(int j = 0;j < b[0].length; j++)
             {
                 b[i][j].setH((10 * (Math.abs(i - g.getRow()))) + (10 * (Math.abs(j - g.getCol()))));
             }
        }
     }




    public void displayHeuristic(Node [][]d) 
    {

        System.out.println("\nThis is a graph of the heuristic values ");

        for(int i = 0;i < d.length; i++) 
        {
            System.out.print("\n");



            for(int j = 0;j < d[0].length; j++) 
            {
                if ((d[i][j].getH() >= 10) && (d[i][j].getH() <= 90))
                {
                    System.out.print(d[i][j].getH() + " ");

                }else if (d[i][j].getH() < 10){
                    
                    System.out.print(d[i][j].getH() + " ");

                }else{
                    System.out.print(d[i][j].getH() + " ");
                }

                System.out.print(" ");
            }
        }
        System.out.println("\n");
    }



    public static Node checkinList(Node node, ArrayList<Node> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).equals(node))
            {
                return list.get(i);
            }
        }

        return null;
    }



    public static void sort(ArrayList<Node> slist)
    {
        int lowestF; 
        Node kNode; 

        for(int i = 0; i < slist.size(); i++)
        {

            lowestF = i;
            
            int b = (slist.size() - 1);
            for(int id = i; id < b; id++) 
            {
                if (slist.get(id + 1).getF() < slist.get(lowestF).getF()) 
                {

                    lowestF = id + 1;
                }
            }

            kNode = slist.get(i);
            slist.set(i, slist.get(lowestF));
            slist.set(lowestF, kNode);
        }
    }
}

