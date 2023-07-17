package implementation;
import java.util.*;
public class SegmentTree {
    private static int[] tree;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        tree=new int[4*n];
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
            arr[i]=sc.nextInt();
        int q=sc.nextInt();
        int[][] query=new int[q][2];
        for(int i=0;i<q;i++)
        {
            query[i][0]=sc.nextInt();
            query[i][1]=sc.nextInt();
        }
        build(0,0,n-1,arr);
        for(int[] qr:query)
        {
            System.out.println(solve(0,0,n-1,qr[0],qr[1]));
        }
    }
    private static void build(int ind,int low,int high,int[] arr)
    {
        if(low==high)
        {
            tree[ind]=arr[low];
            return;
        }
        int mid=(low+high)/2;
        build(2*ind+1,low,mid,arr);
        build(2*ind+2,mid+1,high,arr);
        tree[ind]=Math.max(tree[2*ind+1],tree[2*ind+2]);
    }
    private static int solve(int ind,int low,int high,int l,int r)
    {
        // completely lies - simply return its value
        // do not lie - return INT_MIN
        // overlaps - move into both directions

        // first case - complete range lies inside our query range
        if(low>=l&&high<=r)
            return tree[ind];
        // second case - do not lie inside range
        if(high<l||low>r)return Integer.MIN_VALUE;
        // third case- overlaps
        int mid=(low+high)/2;
        int left=solve(2*ind+1,low,mid,l,r);
        int right=solve(2*ind+2,mid+1,high,l,r);
        return Math.max(left,right);
    }

}
