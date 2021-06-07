package gof.behaviouralpattern.visitor;

interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}