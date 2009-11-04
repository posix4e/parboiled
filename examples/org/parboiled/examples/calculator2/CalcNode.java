/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.examples.calculator2;

import org.parboiled.trees.ImmutableBinaryTreeNode;

/**
 * The AST node for the calculator. The type of the node is carried as a Character that can either contain an operator
 * or be null. In the latter case the AST node is a leaf directly containing a value.
 */
public class CalcNode extends ImmutableBinaryTreeNode<CalcNode> {

    private int value;
    private Character operator;

    public CalcNode(int value) {
        super(null, null);
        this.value = value;
    }

    public CalcNode(Character operator, CalcNode left, CalcNode right) {
        super(left, right);
        this.operator = operator;
    }

    public int getValue() {
        if (operator == null) return value;
        if (left() == null || right() == null) return 0;

        switch (operator) {
            case '+':
                return left().getValue() + right().getValue();
            case '-':
                return left().getValue() - right().getValue();
            case '*':
                return left().getValue() * right().getValue();
            case '/':
                return left().getValue() / right().getValue();
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        return operator == null ?
                "Value " + value :
                "Operator '" + operator + '\'';
    }

}
