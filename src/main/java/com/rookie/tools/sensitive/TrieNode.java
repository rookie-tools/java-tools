package com.rookie.tools.sensitive;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 49540 on 2018/6/1.
 */
public class TrieNode {
    private boolean end = false;

    private Map<Character,TrieNode> subNode = new HashMap<>();

    void addSubNode(Character key,TrieNode node)
    {
        subNode.put(key,node);
    }
    TrieNode getSubNode(Character key)
    {
        return subNode.get(key);
    }

    boolean isKeyWordEnd()
    {
        return end;
    }

    void setKeyWordEnd(boolean end)
    {
        this.end = end;
    }

    int getNodeCount()
    {
        return subNode.size();
    }
}
