package pattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class NeuronNetwork {
    public static void main(String[] args) {
        Neuron neuron1 = new Neuron();
        Neuron neuron2 = new Neuron();
        NeuronLayer layer1 = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();
        layer1.add(new Neuron());

        neuron1.connectTo(neuron2);
        neuron1.connectTo(layer1);
        neuron1.connectTo(layer2);
        System.out.println("Neuron1.out: " + neuron1.out.size());
        System.out.println("Neuron2.in: " + neuron2.in.size());
        System.out.println("Layer1.in: " + layer1.get(0).in.size());
    }
}

interface NeuronInterface extends Iterable<Neuron> {
    default public void connectTo(NeuronInterface other) {
        if(this == other) return;
        for(Neuron from : this) {
            for (Neuron to: other) {
                from.out.add(to);
                to.in.add(from);
            }
        }
    }
}

class Neuron implements NeuronInterface{
    public ArrayList<Neuron> in = new ArrayList<>(), out = new ArrayList<>();

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }
}

class NeuronLayer extends ArrayList<Neuron> implements NeuronInterface {

}
