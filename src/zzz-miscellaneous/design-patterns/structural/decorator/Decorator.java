// https://www.baeldung.com/java-decorator-pattern
// A Decorator pattern can be used to attach additional responsibilities to an object either statically or dynamically
// ex: pizza toppings or add'ons in coffee machine - helps to reduce no. classes by just having a base class and diff combinations, instead of many classes
// note: decorater will have both has-a and is-a relationship
// https://www.youtube.com/watch?v=w6a9MXUwcfY&list=PL6W8uoQQ2c61X_9e6Net0WdYZidm7zooW&index=6

interface ChristmasTree {
    String decorate();
}

class ChristmasTreeImpl implements ChristmasTree {
    @Override
    public String decorate() {
        return "Christmas tree";
    }
}

abstract class TreeDecorator implements ChristmasTree {
    private final ChristmasTree tree;

    public TreeDecorator(ChristmasTree tree) {
        this.tree = tree;
    }

    @Override
    public String decorate() {
        return tree.decorate();
    }
}

class Garland extends TreeDecorator {

    public Garland(ChristmasTree tree) {
        super(tree);
    }

    public String decorate() {
        return super.decorate() + decorateWithGarland();
    }

    private String decorateWithGarland() {
        return " with Garland";
    }
}

class BubbleLights extends TreeDecorator {

    public BubbleLights(ChristmasTree tree) {
        super(tree);
    }

    public String decorate() {
        return super.decorate() + decorateWithBubbleLights();
    }

    private String decorateWithBubbleLights() {
        return " with Bubble Lights";
    }
}

class DecoratorPatternDriver {
    public static void main(String[] args) {
        ChristmasTree tree = new ChristmasTreeImpl();
        System.out.println(tree.decorate());
        // Christmas tree with just one Garland
        ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
        System.out.println(tree1.decorate());

        // Christmas tree with two Garlands and one Bubble lights
        ChristmasTree tree2 = new BubbleLights(new Garland(new Garland(new ChristmasTreeImpl())));
        System.out.println(tree2.decorate());
    }
}