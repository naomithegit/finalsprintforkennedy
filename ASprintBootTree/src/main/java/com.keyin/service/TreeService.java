package com.keyin.service;

import com.keyin.entity.TreeEntity;
import com.keyin.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {
    @Autowired
    private TreeRepository treeRepository;

    public TreeEntity saveTree(String inputNumbers, String treeStructure) {
        TreeEntity treeEntity = new TreeEntity(inputNumbers, treeStructure);
        return treeRepository.save(treeEntity);
    }

    public List<TreeEntity> getAllTrees() {
        return treeRepository.findAll();
    }

    public static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    public TreeNode constructTree(int[] numbers) {
        TreeNode root = null;
        for (int num : numbers) {
            root = insert(root, num);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) return new TreeNode(value);
        if (value < node.value) node.left = insert(node.left, value);
        else node.right = insert(node.right, value);
        return node;
    }

    public String treeToJson(TreeNode node) {
        if (node == null) return "null";
        return String.format("{\"value\":%d,\"left\":%s,\"right\":%s}",
                node.value, treeToJson(node.left), treeToJson(node.right));
    }
}