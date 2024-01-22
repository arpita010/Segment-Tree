 class SegmentTree
    {
        int[] arr;
        int[] tree;
        int n;
        public SegmentTree(int n,int[] arr)
        {
            this.n=n;
            this.arr=arr;
            this.tree=new int[n*4];
            buildTree(0,n-1,1);
        }
        private void buildTree(int tl,int tr,int idx)
        {
            if(tl>tr)return;
            if(tl==tr)
            {
                tree[idx]=arr[tl];
                return;
            }
            int tm=tl+(tr-tl)/2;
            buildTree(tl,tm,idx*2);
            buildTree(tm+1,tr,idx*2+1);
            tree[idx]=tree[idx*2]+tree[idx*2+1];
        }
        public int getSum(int l,int r)
        {
            return sum(0,n-1,l,r,1);
        }
        private int sum(int tl,int tr,int l,int r,int idx)
        {
            if(tl>tr)return 0;
            if(tr<l||tl>r)return 0;
            if(tl>=l&&tr<=r)return tree[idx];
            int tm=tl+(tr-tl)/2;
            return sum(tl,tm,l,r,idx*2)+sum(tm+1,tr,l,r,idx*2+1);
        }
        public void update(int index,int val)
        {
            arr[index]=val;
            update(0,n-1,1,index,val);
        }
        private void update(int tl,int tr,int idx,int index,int val)
        {
            if(tl>tr)return;
            if(index<tl||index>tr)return;
            if(tl==tr)
            {
                tree[idx]=arr[tl];
                return;
            }
            int tm=tl+(tr-tl)/2;
            update(tl,tm,idx*2,index,val);
            update(tm+1,tr,idx*2+1,index,val);
            tree[idx]=tree[idx*2]+tree[idx*2+1];
        }
    }
