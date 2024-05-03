package com.example.searchapp.search

class TreeNode(var isWord:Boolean = false) {
    val children: MutableMap<Char, TreeNode> = mutableMapOf()
}

class Tree {

    private val root = TreeNode()

    fun insertWord(word: String) {
        var current = root
        word.forEach { char ->
            current = current.children.getOrPut(char) { TreeNode() }
        }
        current.isWord = true
    }

    fun searchWord(prefix: String): List<String> {
        var current = root
        prefix.forEach { char ->
            current = current.children[char] ?: return emptyList()
        }
        return collectWords(current, prefix)
    }

    private fun collectWords(node: TreeNode, prefix: String): List<String> {
        val results = mutableListOf<String>()
        if (node.isWord) {
            results.add(prefix)
        }
        node.children.forEach { (char, nextNode) ->
            results.addAll(collectWords(nextNode, prefix + char))
        }
        return results
    }

}