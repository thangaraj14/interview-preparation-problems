package behaviouralpattern.visitor;

interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}