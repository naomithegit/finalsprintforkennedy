//package com.keyin.controller;
//
//import com.keyin.entity.TreeEntity;
//import com.keyin.service.TreeService;
//import com.keyin.service.TreeService.TreeNode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Controller
//public class TreeController {
//    @Autowired
//    private TreeService treeService;
//
//    @GetMapping("/enter-numbers")
//    public String enterNumbers() {
//        return "enter-numbers";
//    }
//
//    @PostMapping("/process-numbers")
//    public String processNumbers(@RequestParam String numbers, Model model) {
//        int[] parsedNumbers = Arrays.stream(numbers.split(","))
//                .mapToInt(Integer::parseInt)
//                .toArray();
//        TreeNode root = treeService.constructTree(parsedNumbers);
//        String treeJson = treeService.treeToJson(root);
//
//        treeService.saveTree(numbers, treeJson);
//
//        model.addAttribute("treeJson", treeJson);
//        return "tree-result";
//    }
//
//    @GetMapping("/previous-trees")
//    public String previousTrees(Model model) {
//        List<TreeEntity> trees = treeService.getAllTrees();
//        model.addAttribute("trees", trees);
//        return "previous-trees";
//    }
//}
//
//
package com.keyin.controller;

import com.keyin.entity.TreeEntity;
import com.keyin.service.TreeService;
import com.keyin.service.TreeService.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TreeController {
    @Autowired
    private TreeService treeService;

    @GetMapping("/enter-numbers")
    public String enterNumbers() {
        return "enter-numbers";
    }

    @PostMapping("/process-numbers")
    public String processNumbers(@RequestParam String numbers, Model model) {
        try {
            int[] parsedNumbers = Arrays.stream(numbers.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            TreeNode root = treeService.constructTree(parsedNumbers);
            String treeJson = treeService.treeToJson(root);

            treeService.saveTree(numbers, treeJson);

            model.addAttribute("treeJson", treeJson);
            return "tree-result";
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Invalid input. Please enter a comma-separated list of numbers.");
            return "enter-numbers";
        }
    }

    @GetMapping("/previous-trees")
    public String previousTrees(Model model) {
        List<TreeEntity> trees = treeService.getAllTrees();
        model.addAttribute("trees", trees);
        return "previous-trees";
    }
}