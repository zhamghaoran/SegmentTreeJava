package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a,b;
        a = sc.nextInt();
        b = sc.nextInt();
        tree[] tree = new tree[10000100];
        int[] val = new int[10000100];
        for(int i = 1;i <= a;i ++) {
            val[i] = sc.nextInt();
        }
        build(tree,1,a,1,val);
        int opt;
        int x,y,z;
        for(int i = 1;i <= b;i ++)  {
            opt = sc.nextInt();
            if(opt == 1) {
                x = sc.nextInt();
                y = sc.nextInt();
                z = sc.nextInt();
                change(tree,x,y,1,z);
            }
            else {
                x = sc.nextInt();
                y = sc.nextInt();
                long ans;
                ans = get(tree,x,y,1);
                System.out.println(ans);
            }
        }
    }
    static class tree {
        int val;
        int l;
        int r;
        int add;
    }
    static long get(tree tree[],int l,int r,int num) {
        long ans = 0;
        if(tree[num].l == l && tree[num].r == r) {
            ans += tree[num].val;
            ans += (long) tree[num].add * (tree[num].r - tree[num].l + 1);
            return ans;
        }
        else {
            ans += (long) tree[num].add * (r - l + 1);
            int mid = tree[num].r + tree[num].l >> 1;
            if(r <= mid)
                ans += get(tree,l,r,num << 1);
            else if(l > mid)
                ans += get(tree,l,r,num << 1 | 1);
            else {
                ans += get(tree,l,mid,num << 1);
                ans += get(tree,mid + 1,r,num << 1 | 1);
            }
        }
        return ans;
    }
    static void change(tree tree[],int l,int r,int num,int add) {
        if(tree[num].l == l && tree[num].r == r) {
            tree[num].add += add;
        }
        else {
            int mid = tree[num].l + tree[num].r >> 1;
            if(r <= mid)
                change(tree,l,r,num << 1,add);
            else if(l > mid)
                change(tree,l,r,num << 1 | 1,add);
            else {
                change(tree,l,mid,num << 1,add);
                change(tree,mid + 1,r,num << 1 | 1,add);
            }
            tree[num].val = tree[num << 1].val + tree[num << 1 | 1].val;
            tree[num].val += tree[num << 1].add * (tree[num << 1].r - tree[num << 1].l + 1);
            tree[num].val += tree[num << 1 | 1].add * (tree[num << 1 | 1].r - tree[num << 1 | 1].l + 1);
        }
    }
    static void build(tree[] tree, int l, int r, int num,int[] val) {
        tree[num] = new tree();
        tree[num].l = l;
        tree[num].r = r;
        if (l == r) {
            tree[num].val = val[l];
        }
        else {
            int mid = l + r >> 1;
            build(tree,l,mid,num << 1,val);
            build(tree,mid + 1,r,num << 1 | 1,val);
            tree[num].val = tree[num << 1].val + tree[num << 1 | 1].val;
        }
    }
}
