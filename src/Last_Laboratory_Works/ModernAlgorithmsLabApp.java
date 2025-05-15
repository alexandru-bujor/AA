package Last_Laboratory_Works;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class ModernAlgorithmsLabApp {
    private JFrame frame;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Color bgColor = new Color(240, 240, 245);
    private Color primaryColor = new Color(70, 130, 180);
    private Color secondaryColor = new Color(100, 149, 237);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ModernAlgorithmsLabApp window = new ModernAlgorithmsLabApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ModernAlgorithmsLabApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Algorithms Laboratory Suite");
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(bgColor);

        // Create modern menu bar
        JMenuBar menuBar = new ModernMenuBar();
        JMenu labsMenu = new ModernMenu("Laboratory Works");

        // Menu items for each lab
        ModernMenuItem lab3Item = new ModernMenuItem("Lab 3: Graph Traversals");
        ModernMenuItem lab5Item = new ModernMenuItem("Lab 5: Path Finding");
        ModernMenuItem lab7Item = new ModernMenuItem("Lab 7: Minimum Spanning Trees");

        // Add items to menu
        labsMenu.add(lab3Item);
        labsMenu.add(lab5Item);
        labsMenu.add(lab7Item);
        menuBar.add(labsMenu);

        // Add help menu
        JMenu helpMenu = new ModernMenu("Help");
        ModernMenuItem aboutItem = new ModernMenuItem("About");
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        // Create card layout for switching between panels
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(bgColor);

        // Create panels for each lab
        ModernLab3Panel lab3Panel = new ModernLab3Panel();
        ModernLab5Panel lab5Panel = new ModernLab5Panel();
        ModernLab7Panel lab7Panel = new ModernLab7Panel();

        // Add panels to card layout
        contentPanel.add(lab3Panel, "Lab3");
        contentPanel.add(lab5Panel, "Lab5");
        contentPanel.add(lab7Panel, "Lab7");

        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Add action listeners for menu items
        lab3Item.addActionListener(e -> {
            cardLayout.show(contentPanel, "Lab3");
            frame.setTitle("Algorithms Laboratory Suite - Graph Traversals");
        });
        lab5Item.addActionListener(e -> {
            cardLayout.show(contentPanel, "Lab5");
            frame.setTitle("Algorithms Laboratory Suite - Path Finding");
        });
        lab7Item.addActionListener(e -> {
            cardLayout.show(contentPanel, "Lab7");
            frame.setTitle("Algorithms Laboratory Suite - Minimum Spanning Trees");
        });

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Algorithms Laboratory Suite\nVersion 1.0\n\n" +
                        "This application demonstrates:\n" +
                        "- Graph traversal algorithms (DFS/BFS)\n" +
                        "- Path finding algorithms (Dijkstra, Floyd-Warshall)\n" +
                        "- Minimum spanning tree algorithms (Prim, Kruskal)",
                "About", JOptionPane.INFORMATION_MESSAGE));
    }
}

// Modern UI Components
class ModernMenuBar extends JMenuBar {
    public ModernMenuBar() {
        setBackground(new Color(60, 60, 70));
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }
}

class ModernMenu extends JMenu {
    public ModernMenu(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}

class ModernMenuItem extends JMenuItem {
    public ModernMenuItem(String text) {
        super(text);
        setBackground(new Color(80, 80, 90));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(new EmptyBorder(5, 15, 5, 15));
    }
}

// Lab 3 Panel with Graph Visualization
class ModernLab3Panel extends JPanel {
    private JTextArea outputArea;
    private JSpinner nodeCountSpinner;
    private JComboBox<String> algorithmComboBox;
    private GraphPanel graphPanel;
    private JButton stepButton;
    private JButton runAllButton;
    private Graph graph;
    private boolean isRunning = false;
    private Color primaryColor = new Color(70, 130, 180);

    public ModernLab3Panel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 245));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Input panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(250, 250, 255));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setLayout(new GridLayout(1, 6, 10, 10));

        controlPanel.add(new JLabel("Nodes:"));
        nodeCountSpinner = new JSpinner(new SpinnerNumberModel(10, 3, 50, 1));
        controlPanel.add(nodeCountSpinner);

        String[] algorithms = {"BFS", "DFS"};
        algorithmComboBox = new JComboBox<>(algorithms);
        controlPanel.add(algorithmComboBox);

        JButton generateButton = new JButton("Generate");
        styleButton(generateButton);
        generateButton.addActionListener(e -> generateGraph());
        controlPanel.add(generateButton);

        stepButton = new JButton("Step");
        styleButton(stepButton);
        stepButton.addActionListener(e -> stepAlgorithm());
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);

        runAllButton = new JButton("Run All");
        styleButton(runAllButton);
        runAllButton.addActionListener(e -> runAlgorithm());
        runAllButton.setEnabled(false);
        controlPanel.add(runAllButton);

        add(controlPanel, BorderLayout.NORTH);

        // Main content area
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        contentPanel.setBackground(new Color(240, 240, 245));

        // Graph visualization panel
        graphPanel = new GraphPanel();
        contentPanel.add(new JScrollPane(graphPanel));

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Output"));
        contentPanel.add(outputScroll);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void generateGraph() {
        try {
            int nodeCount = (Integer) nodeCountSpinner.getValue();
            graph = new Graph(nodeCount);
            graphPanel.setGraph(graph);
            outputArea.append("Generated graph with " + nodeCount + " nodes\n");
            stepButton.setEnabled(true);
            runAllButton.setEnabled(true);
            isRunning = false;
            graphPanel.resetHighlights();
            repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stepAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isRunning) {
            String algorithm = (String) algorithmComboBox.getSelectedItem();
            if ("BFS".equals(algorithm)) {
                graph.initBFS();
            } else {
                graph.initDFS();
            }
            isRunning = true;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        String result;
        if ("BFS".equals(algorithm)) {
            result = graph.stepBFS();
        } else {
            result = graph.stepDFS();
        }

        if (result != null) {
            outputArea.append(result + "\n");
            graphPanel.setVisitedNodes(graph.getVisitedNodes());
            graphPanel.setCurrentNode(graph.getCurrentNode());
            graphPanel.repaint();
        } else {
            outputArea.append("Algorithm completed\n");
            stepButton.setEnabled(false);
            isRunning = false;
        }
    }

    private void runAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        long startTime = System.nanoTime();

        if ("BFS".equals(algorithm)) {
            outputArea.append("BFS Traversal: " + graph.bfs(0) + "\n");
        } else {
            outputArea.append("DFS Traversal: " + graph.dfs(0) + "\n");
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6;
        outputArea.append("Execution time: " + duration + " ms\n");

        graphPanel.setVisitedNodes(graph.getVisitedNodes());
        graphPanel.repaint();
    }
}

class GraphPanel extends JPanel {
    private Graph graph;
    private Set<Integer> visitedNodes = new HashSet<>();
    private Integer currentNode = null;
    private Map<Integer, Point> nodePositions = new HashMap<>();
    private static final int NODE_RADIUS = 20;

    public GraphPanel() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        calculateNodePositions();
        resetHighlights();
        repaint();
    }

    public void setVisitedNodes(Set<Integer> visitedNodes) {
        this.visitedNodes = visitedNodes;
        repaint();
    }

    public void setCurrentNode(Integer currentNode) {
        this.currentNode = currentNode;
        repaint();
    }

    public void resetHighlights() {
        visitedNodes.clear();
        currentNode = null;
        repaint();
    }

    private void calculateNodePositions() {
        if (graph == null) return;

        int nodeCount = graph.getNodeCount();
        int centerX = getPreferredSize().width / 2;
        int centerY = getPreferredSize().height / 2;
        int radius = Math.min(centerX, centerY) - 50;

        for (int i = 0; i < nodeCount; i++) {
            double angle = 2 * Math.PI * i / nodeCount;
            int x = centerX + (int)(radius * Math.sin(angle));
            int y = centerY - (int)(radius * Math.cos(angle));
            nodePositions.put(i, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null) return;

        // Draw edges
        g2d.setColor(new Color(200, 200, 200));
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    Point p1 = nodePositions.get(i);
                    Point p2 = nodePositions.get(j);
                    g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
                }
            }
        }

        // Draw nodes
        for (int i = 0; i < graph.getNodeCount(); i++) {
            Point p = nodePositions.get(i);

            // Draw node
            if (currentNode != null && currentNode == i) {
                g2d.setColor(new Color(255, 215, 0)); // Gold for current node
            } else if (visitedNodes.contains(i)) {
                g2d.setColor(new Color(50, 205, 50)); // Lime green for visited
            } else {
                g2d.setColor(new Color(70, 130, 180)); // Steel blue for unvisited
            }

            g2d.fill(new Ellipse2D.Double(p.x - NODE_RADIUS, p.y - NODE_RADIUS,
                    NODE_RADIUS * 2, NODE_RADIUS * 2));

            // Draw node label
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String label = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label, p.x - labelWidth/2, p.y + labelHeight/4);
        }
    }
}

class Graph {
    private int[][] adjacencyMatrix;
    private int nodeCount;
    private Queue<Integer> bfsQueue;
    private Stack<Integer> dfsStack;
    private boolean[] visited;
    private Set<Integer> visitedNodes = new HashSet<>();
    private Integer currentNode = null;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        this.adjacencyMatrix = new int[nodeCount][nodeCount];
        Random random = new Random();

        // Generate random edges
        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                if (random.nextDouble() < 0.3) { // 30% chance of edge
                    adjacencyMatrix[i][j] = 1;
                    adjacencyMatrix[j][i] = 1;
                }
            }
        }
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public Set<Integer> getVisitedNodes() {
        return visitedNodes;
    }

    public Integer getCurrentNode() {
        return currentNode;
    }

    public void initBFS() {
        bfsQueue = new LinkedList<>();
        visited = new boolean[nodeCount];
        visitedNodes.clear();
        currentNode = null;

        visited[0] = true;
        bfsQueue.add(0);
        visitedNodes.add(0);
        currentNode = 0;
    }

    public void initDFS() {
        dfsStack = new Stack<>();
        visited = new boolean[nodeCount];
        visitedNodes.clear();
        currentNode = null;

        visited[0] = true;
        dfsStack.push(0);
        visitedNodes.add(0);
        currentNode = 0;
    }

    public String stepBFS() {
        if (bfsQueue.isEmpty()) return null;

        int node = bfsQueue.poll();
        currentNode = node;

        StringBuilder result = new StringBuilder();
        result.append("Visited node ").append(node);

        for (int i = 0; i < nodeCount; i++) {
            if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                visited[i] = true;
                bfsQueue.add(i);
                visitedNodes.add(i);
                result.append(", discovered node ").append(i);
            }
        }

        return result.toString();
    }

    public String stepDFS() {
        if (dfsStack.isEmpty()) return null;

        int node = dfsStack.peek();
        currentNode = node;

        boolean foundUnvisited = false;
        for (int i = 0; i < nodeCount; i++) {
            if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfsStack.push(i);
                visitedNodes.add(i);
                foundUnvisited = true;
                return "Visited node " + i;
            }
        }

        if (!foundUnvisited) {
            dfsStack.pop();
            if (!dfsStack.isEmpty()) {
                return "Backtracked to node " + dfsStack.peek();
            }
        }

        return null;
    }

    public String bfs(int startNode) {
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[nodeCount];
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.append(node).append(" ");

            for (int i = 0; i < nodeCount; i++) {
                if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return result.toString();
    }

    public String dfs(int startNode) {
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[nodeCount];
        dfsUtil(startNode, visited, result);
        return result.toString();
    }

    private void dfsUtil(int node, boolean[] visited, StringBuilder result) {
        visited[node] = true;
        result.append(node).append(" ");

        for (int i = 0; i < nodeCount; i++) {
            if (adjacencyMatrix[node][i] == 1 && !visited[i]) {
                dfsUtil(i, visited, result);
            }
        }
    }
}

class ModernLab5Panel extends JPanel {
    private JTextArea outputArea;
    private JSpinner nodeCountSpinner;
    private JComboBox<String> algorithmComboBox;
    private GraphVisualizationPanel graphPanel;
    private PathVisualizationPanel pathPanel;
    private JButton stepButton;
    private JButton runAllButton;
    private WeightedGraph graph;
    private boolean isRunning = false;
    private Color primaryColor = new Color(70, 130, 180);
    private int[][] floydResult;
    private int[] dijkstraResult;

    public ModernLab5Panel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 245));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Input panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(250, 250, 255));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setLayout(new GridLayout(1, 7, 10, 10));

        controlPanel.add(new JLabel("Nodes:"));
        nodeCountSpinner = new JSpinner(new SpinnerNumberModel(8, 3, 20, 1));
        controlPanel.add(nodeCountSpinner);

        String[] algorithms = {"Dijkstra", "Floyd-Warshall"};
        algorithmComboBox = new JComboBox<>(algorithms);
        controlPanel.add(algorithmComboBox);

        JButton generateButton = new JButton("Generate");
        styleButton(generateButton);
        generateButton.addActionListener(e -> generateGraph());
        controlPanel.add(generateButton);

        stepButton = new JButton("Step");
        styleButton(stepButton);
        stepButton.addActionListener(e -> stepAlgorithm());
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);

        runAllButton = new JButton("Run All");
        styleButton(runAllButton);
        runAllButton.addActionListener(e -> runAlgorithm());
        runAllButton.setEnabled(false);
        controlPanel.add(runAllButton);

        JButton resetButton = new JButton("Reset");
        styleButton(resetButton);
        resetButton.addActionListener(e -> resetVisualization());
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.NORTH);

        // Main content area
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        contentPanel.setBackground(new Color(240, 240, 245));

        // Graph visualization panel
        graphPanel = new GraphVisualizationPanel();
        contentPanel.add(new JScrollPane(graphPanel));

        // Path visualization panel
        pathPanel = new PathVisualizationPanel();
        contentPanel.add(new JScrollPane(pathPanel));

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Algorithm Steps"));
        contentPanel.add(outputScroll);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void generateGraph() {
        try {
            int nodeCount = (Integer) nodeCountSpinner.getValue();
            graph = new WeightedGraph(nodeCount);
            graphPanel.setGraph(graph);
            pathPanel.setGraph(graph);
            outputArea.setText("Generated weighted graph with " + nodeCount + " nodes\n");
            stepButton.setEnabled(true);
            runAllButton.setEnabled(true);
            isRunning = false;
            resetVisualization();
            repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stepAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        String result = null;

        if ("Dijkstra".equals(algorithm)) {
            result = graph.stepDijkstra();
            if (result == null) {
                dijkstraResult = graph.getDijkstraResult();
                pathPanel.setDijkstraResult(dijkstraResult);
                outputArea.append("Dijkstra completed. Showing shortest paths from node 0\n");
            } else {
                outputArea.append(result + "\n");
            }
        } else { // Floyd-Warshall
            result = graph.stepFloydWarshall();
            if (result == null) {
                floydResult = graph.getFloydResult();
                pathPanel.setFloydResult(floydResult);
                outputArea.append("Floyd-Warshall completed. Showing all pairs shortest paths\n");
            } else {
                outputArea.append(result + "\n");
            }
        }

        if (result == null) {
            stepButton.setEnabled(false);
            isRunning = false;
        }

        graphPanel.setCurrentStep(graph.getCurrentStep());
        graphPanel.repaint();
        pathPanel.repaint();
    }

    private void runAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        long startTime = System.nanoTime();

        if ("Dijkstra".equals(algorithm)) {
            dijkstraResult = graph.runDijkstra(0);
            pathPanel.setDijkstraResult(dijkstraResult);
            outputArea.append("Dijkstra distances from node 0:\n");
            for (int i = 0; i < dijkstraResult.length; i++) {
                outputArea.append("Node " + i + ": " +
                        (dijkstraResult[i] == Integer.MAX_VALUE ? "∞" : dijkstraResult[i]) + "\n");
            }
        } else {
            floydResult = graph.runFloydWarshall();
            pathPanel.setFloydResult(floydResult);
            outputArea.append("Floyd-Warshall distance matrix:\n");
            for (int[] row : floydResult) {
                for (int val : row) {
                    outputArea.append((val == Integer.MAX_VALUE ? "∞" : val) + "\t");
                }
                outputArea.append("\n");
            }
        }

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6;
        outputArea.append("Execution time: " + duration + " ms\n");

        graphPanel.setCurrentStep(graph.getCurrentStep());
        graphPanel.repaint();
        pathPanel.repaint();
    }

    private void resetVisualization() {
        if (graph != null) {
            graph.resetAlgorithmState();
            graphPanel.setCurrentStep(0);
            pathPanel.clearResults();
            outputArea.setText("Graph reset. Ready to run algorithm.\n");
            isRunning = false;
            stepButton.setEnabled(true);
            graphPanel.repaint();
            pathPanel.repaint();
        }
    }
}

class WeightedGraph {
    private int[][] adjacencyMatrix;
    private int nodeCount;

    // For Dijkstra's algorithm
    private int[] dijkstraDistances;
    private boolean[] dijkstraVisited;
    private int dijkstraCurrentNode = -1;

    // For Floyd-Warshall algorithm
    private int[][] floydDistances;

    // For Prim's algorithm
    private List<Edge> primEdges = new ArrayList<>();
    private boolean[] primIncluded;
    private int[] primParent;
    private int[] primKey;

    // For Kruskal's algorithm
    private List<Edge> kruskalEdges = new ArrayList<>();
    private List<Edge> allEdges = new ArrayList<>();
    private int[] kruskalParent;
    private int kruskalEdgeIndex = 0;

    private int currentStep = 0;

    public WeightedGraph(int nodeCount) {
        this.nodeCount = nodeCount;
        this.adjacencyMatrix = new int[nodeCount][nodeCount];
        Random random = new Random();

        // Generate random edges with weights
        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                if (random.nextDouble() < 0.4) { // 40% chance of edge
                    int weight = random.nextInt(10) + 1;
                    adjacencyMatrix[i][j] = weight;
                    adjacencyMatrix[j][i] = weight;
                    allEdges.add(new Edge(i, j, weight));
                }
            }
        }
    }

    // Common methods
    public int[][] getAdjacencyMatrix() { return adjacencyMatrix; }
    public int getNodeCount() { return nodeCount; }
    public int getCurrentStep() { return currentStep; }

    public void resetAlgorithmState() {
        currentStep = 0;
        dijkstraDistances = null;
        floydDistances = null;
        dijkstraCurrentNode = -1;
        primEdges.clear();
        kruskalEdges.clear();
    }

    // Dijkstra's algorithm methods
    public String stepDijkstra() {
        if (currentStep == 0) {
            // Initialize
            dijkstraDistances = new int[nodeCount];
            dijkstraVisited = new boolean[nodeCount];
            Arrays.fill(dijkstraDistances, Integer.MAX_VALUE);
            dijkstraDistances[0] = 0;
            currentStep++;
            return "Initialized Dijkstra's algorithm. Start node: 0";
        }

        // Find node with minimum distance
        int minDist = Integer.MAX_VALUE;
        int minNode = -1;

        for (int i = 0; i < nodeCount; i++) {
            if (!dijkstraVisited[i] && dijkstraDistances[i] < minDist) {
                minDist = dijkstraDistances[i];
                minNode = i;
            }
        }

        if (minNode == -1) return null; // Algorithm complete

        dijkstraCurrentNode = minNode;
        dijkstraVisited[minNode] = true;
        currentStep++;

        StringBuilder result = new StringBuilder();
        result.append("Processing node ").append(minNode).append(". Updating neighbors:");

        // Update neighbors
        for (int i = 0; i < nodeCount; i++) {
            if (adjacencyMatrix[minNode][i] > 0 && !dijkstraVisited[i]) {
                int newDist = dijkstraDistances[minNode] + adjacencyMatrix[minNode][i];
                if (newDist < dijkstraDistances[i]) {
                    result.append("\n  Node ").append(i).append(": ").append(dijkstraDistances[i])
                            .append(" → ").append(newDist);
                    dijkstraDistances[i] = newDist;
                }
            }
        }
        return result.toString();
    }

    public int[] runDijkstra(int startNode) {
        int[] distances = new int[nodeCount];
        boolean[] visited = new boolean[nodeCount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        for (int count = 0; count < nodeCount - 1; count++) {
            int u = minDistance(distances, visited);
            if (u == -1) break;

            visited[u] = true;

            for (int v = 0; v < nodeCount; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE
                        && distances[u] + adjacencyMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjacencyMatrix[u][v];
                }
            }
        }

        this.dijkstraDistances = distances;
        this.currentStep = nodeCount;
        return distances;
    }

    // Floyd-Warshall algorithm methods
    public String stepFloydWarshall() {
        if (currentStep == 0) {
            // Initialize
            floydDistances = new int[nodeCount][nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (i == j) {
                        floydDistances[i][j] = 0;
                    } else {
                        floydDistances[i][j] = adjacencyMatrix[i][j] == 0 ? Integer.MAX_VALUE : adjacencyMatrix[i][j];
                    }
                }
            }
            currentStep++;
            return "Initialized Floyd-Warshall algorithm. Distance matrix created.";
        }

        if (currentStep > nodeCount) return null; // Algorithm complete

        int k = currentStep - 1; // Current intermediate node
        StringBuilder result = new StringBuilder();
        result.append("Using node ").append(k).append(" as intermediate. Updates:");

        boolean updated = false;
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (floydDistances[i][k] != Integer.MAX_VALUE &&
                        floydDistances[k][j] != Integer.MAX_VALUE) {
                    int newDist = floydDistances[i][k] + floydDistances[k][j];
                    if (newDist < floydDistances[i][j]) {
                        result.append("\n  d[").append(i).append("][").append(j).append("]: ")
                                .append(floydDistances[i][j]).append(" → ").append(newDist);
                        floydDistances[i][j] = newDist;
                        updated = true;
                    }
                }
            }
        }

        currentStep++;
        if (!updated) result.append("\nNo updates in this step");
        return result.toString();
    }

    public int[][] runFloydWarshall() {
        int[][] dist = new int[nodeCount][nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                dist[i][j] = adjacencyMatrix[i][j] == 0 ?
                        (i == j ? 0 : Integer.MAX_VALUE) : adjacencyMatrix[i][j];
            }
        }

        for (int k = 0; k < nodeCount; k++) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        this.floydDistances = dist;
        this.currentStep = nodeCount + 1;
        return dist;
    }

    // Prim's algorithm methods
    public String stepPrim() {
        if (currentStep == 0) {
            // Initialize
            primIncluded = new boolean[nodeCount];
            primParent = new int[nodeCount];
            primKey = new int[nodeCount];

            Arrays.fill(primKey, Integer.MAX_VALUE);
            primKey[0] = 0;
            primParent[0] = -1;

            currentStep++;
            return "Initialized Prim's algorithm. Starting from node 0";
        }

        if (currentStep > nodeCount) return null; // Algorithm complete

        // Find vertex with minimum key value
        int u = -1;
        int min = Integer.MAX_VALUE;
        for (int v = 0; v < nodeCount; v++) {
            if (!primIncluded[v] && primKey[v] < min) {
                min = primKey[v];
                u = v;
            }
        }

        if (u == -1) return null; // No more vertices to include

        primIncluded[u] = true;
        String result = "Added node " + u + " to MST";

        if (primParent[u] != -1) {
            Edge edge = new Edge(primParent[u], u, adjacencyMatrix[primParent[u]][u]);
            primEdges.add(edge);
            result += " via edge " + edge.src + "-" + edge.dest + " (weight " + edge.weight + ")";
        }

        // Update key values of adjacent vertices
        for (int v = 0; v < nodeCount; v++) {
            if (adjacencyMatrix[u][v] > 0 && !primIncluded[v] && adjacencyMatrix[u][v] < primKey[v]) {
                primParent[v] = u;
                primKey[v] = adjacencyMatrix[u][v];
            }
        }

        currentStep++;
        return result;
    }

    public List<Edge> runPrim() {
        primEdges.clear();
        primIncluded = new boolean[nodeCount];
        primParent = new int[nodeCount];
        primKey = new int[nodeCount];

        Arrays.fill(primKey, Integer.MAX_VALUE);
        primKey[0] = 0;
        primParent[0] = -1;

        for (int count = 0; count < nodeCount - 1; count++) {
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int v = 0; v < nodeCount; v++) {
                if (!primIncluded[v] && primKey[v] < min) {
                    min = primKey[v];
                    u = v;
                }
            }

            if (u == -1) break;

            primIncluded[u] = true;

            if (primParent[u] != -1) {
                primEdges.add(new Edge(primParent[u], u, adjacencyMatrix[primParent[u]][u]));
            }

            for (int v = 0; v < nodeCount; v++) {
                if (adjacencyMatrix[u][v] > 0 && !primIncluded[v] && adjacencyMatrix[u][v] < primKey[v]) {
                    primParent[v] = u;
                    primKey[v] = adjacencyMatrix[u][v];
                }
            }
        }

        currentStep = nodeCount + 1;
        return primEdges;
    }

    // Kruskal's algorithm methods
    public String stepKruskal() {
        if (currentStep == 0) {
            // Initialize
            kruskalParent = new int[nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                kruskalParent[i] = i;
            }

            allEdges.sort(Comparator.comparingInt(e -> e.weight));
            kruskalEdgeIndex = 0;
            kruskalEdges.clear();

            currentStep++;
            return "Initialized Kruskal's algorithm. Sorted all edges by weight.";
        }

        if (kruskalEdgeIndex >= allEdges.size()) return null; // Algorithm complete

        Edge nextEdge = allEdges.get(kruskalEdgeIndex++);
        int x = find(kruskalParent, nextEdge.src);
        int y = find(kruskalParent, nextEdge.dest);

        if (x != y) {
            kruskalEdges.add(nextEdge);
            union(kruskalParent, x, y);
            return "Added edge " + nextEdge.src + "-" + nextEdge.dest +
                    " (weight " + nextEdge.weight + ") to MST";
        } else {
            return "Skipped edge " + nextEdge.src + "-" + nextEdge.dest +
                    " (would create cycle)";
        }
    }

    public List<Edge> runKruskal() {
        kruskalEdges.clear();
        kruskalParent = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            kruskalParent[i] = i;
        }

        allEdges.sort(Comparator.comparingInt(e -> e.weight));

        for (Edge nextEdge : allEdges) {
            int x = find(kruskalParent, nextEdge.src);
            int y = find(kruskalParent, nextEdge.dest);

            if (x != y) {
                kruskalEdges.add(nextEdge);
                union(kruskalParent, x, y);
            }
        }

        currentStep = allEdges.size() + 1;
        return kruskalEdges;
    }

    // Helper methods
    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < nodeCount; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        parent[yRoot] = xRoot;
    }

    // Getter methods for results
    public int[] getDijkstraResult() { return dijkstraDistances; }
    public int[][] getFloydResult() { return floydDistances; }
    public List<Edge> getPrimResult() { return primEdges; }
    public List<Edge> getKruskalResult() { return kruskalEdges; }
}



class GraphVisualizationPanel extends JPanel {
    private WeightedGraph graph;
    private int currentStep = 0;
    private Map<Integer, Point> nodePositions = new HashMap<>();
    private static final int NODE_RADIUS = 20;

    public GraphVisualizationPanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Graph Visualization"));
    }

    public void setGraph(WeightedGraph graph) {
        this.graph = graph;
        calculateNodePositions();
        repaint();
    }

    public void setCurrentStep(int step) {
        this.currentStep = step;
        repaint();
    }

    private void calculateNodePositions() {
        if (graph == null) return;

        int nodeCount = graph.getNodeCount();
        int centerX = getPreferredSize().width / 2;
        int centerY = getPreferredSize().height / 2;
        int radius = Math.min(centerX, centerY) - 50;

        for (int i = 0; i < nodeCount; i++) {
            double angle = 2 * Math.PI * i / nodeCount;
            int x = centerX + (int)(radius * Math.sin(angle));
            int y = centerY - (int)(radius * Math.cos(angle));
            nodePositions.put(i, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null) return;

        // Draw edges
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    Point p1 = nodePositions.get(i);
                    Point p2 = nodePositions.get(j);

                    // Draw edge
                    g2d.setColor(new Color(150, 150, 150));
                    g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));

                    // Draw weight
                    g2d.setColor(Color.BLACK);
                    int midX = (p1.x + p2.x) / 2;
                    int midY = (p1.y + p2.y) / 2;
                    g2d.drawString(String.valueOf(adjacencyMatrix[i][j]), midX, midY);
                }
            }
        }

        // Draw nodes
        for (int i = 0; i < graph.getNodeCount(); i++) {
            Point p = nodePositions.get(i);

            // Draw node
            g2d.setColor(new Color(70, 130, 180)); // Steel blue
            g2d.fill(new Ellipse2D.Double(p.x - NODE_RADIUS, p.y - NODE_RADIUS,
                    NODE_RADIUS * 2, NODE_RADIUS * 2));

            // Draw node label
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String label = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label, p.x - labelWidth/2, p.y + labelHeight/4);
        }
    }
}

class PathVisualizationPanel extends JPanel {
    private WeightedGraph graph;
    private int[] dijkstraResult;
    private int[][] floydResult;

    public PathVisualizationPanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Path Visualization"));
    }

    public void setGraph(WeightedGraph graph) {
        this.graph = graph;
        repaint();
    }

    public void setDijkstraResult(int[] result) {
        this.dijkstraResult = result;
        this.floydResult = null;
        repaint();
    }

    public void setFloydResult(int[][] result) {
        this.floydResult = result;
        this.dijkstraResult = null;
        repaint();
    }

    public void clearResults() {
        this.dijkstraResult = null;
        this.floydResult = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null) return;

        if (dijkstraResult != null) {
            drawDijkstraResult(g2d);
        } else if (floydResult != null) {
            drawFloydResult(g2d);
        } else {
            g2d.setColor(Color.BLACK);
            g2d.drawString("Run an algorithm to see path visualization", 50, 50);
        }
    }

    private void drawDijkstraResult(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Shortest Paths from Node 0", 20, 30);

        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        int y = 60;
        for (int i = 0; i < dijkstraResult.length; i++) {
            String distance = dijkstraResult[i] == Integer.MAX_VALUE ? "∞" : String.valueOf(dijkstraResult[i]);
            g2d.drawString("Node " + i + ": " + distance, 30, y);
            y += 25;
        }
    }

    private void drawFloydResult(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("All Pairs Shortest Paths", 20, 30);

        g2d.setFont(new Font("Consolas", Font.PLAIN, 12));
        int cellSize = 30;
        int startX = 50;
        int startY = 60;

        // Draw column headers
        for (int i = 0; i < floydResult.length; i++) {
            g2d.drawString(String.valueOf(i), startX + (i+1)*cellSize, startY - 5);
        }

        // Draw row headers and matrix
        for (int i = 0; i < floydResult.length; i++) {
            g2d.drawString(String.valueOf(i), startX - 15, startY + i*cellSize + 15);

            for (int j = 0; j < floydResult[i].length; j++) {
                String val = floydResult[i][j] == Integer.MAX_VALUE ? "∞" : String.valueOf(floydResult[i][j]);
                g2d.drawString(val, startX + (j+1)*cellSize, startY + i*cellSize + 15);
            }
        }
    }
}

class ModernLab7Panel extends JPanel {
    private JTextArea outputArea;
    private JSpinner nodeCountSpinner;
    private JComboBox<String> algorithmComboBox;
    private MSTGraphPanel graphPanel;
    private MSTResultPanel resultPanel;
    private JButton stepButton;
    private JButton runAllButton;
    private WeightedGraph graph;
    private boolean isRunning = false;
    private Color primaryColor = new Color(70, 130, 180);
    private List<Edge> mstEdges = new ArrayList<>();

    public ModernLab7Panel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 240, 245));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Input panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(250, 250, 255));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setLayout(new GridLayout(1, 7, 10, 10));

        controlPanel.add(new JLabel("Nodes:"));
        nodeCountSpinner = new JSpinner(new SpinnerNumberModel(8, 3, 20, 1));
        controlPanel.add(nodeCountSpinner);

        String[] algorithms = {"Prim", "Kruskal"};
        algorithmComboBox = new JComboBox<>(algorithms);
        controlPanel.add(algorithmComboBox);

        JButton generateButton = new JButton("Generate");
        styleButton(generateButton);
        generateButton.addActionListener(e -> generateGraph());
        controlPanel.add(generateButton);

        stepButton = new JButton("Step");
        styleButton(stepButton);
        stepButton.addActionListener(e -> stepAlgorithm());
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);

        runAllButton = new JButton("Run All");
        styleButton(runAllButton);
        runAllButton.addActionListener(e -> runAlgorithm());
        runAllButton.setEnabled(false);
        controlPanel.add(runAllButton);

        JButton resetButton = new JButton("Reset");
        styleButton(resetButton);
        resetButton.addActionListener(e -> resetVisualization());
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.NORTH);

        // Main content area
        JPanel contentPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        contentPanel.setBackground(new Color(240, 240, 245));

        // Graph visualization panel
        graphPanel = new MSTGraphPanel();
        contentPanel.add(new JScrollPane(graphPanel));

        // MST result panel
        resultPanel = new MSTResultPanel();
        contentPanel.add(new JScrollPane(resultPanel));

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(BorderFactory.createTitledBorder("Algorithm Steps"));
        contentPanel.add(outputScroll);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void generateGraph() {
        try {
            int nodeCount = (Integer) nodeCountSpinner.getValue();
            graph = new WeightedGraph(nodeCount);
            graphPanel.setGraph(graph);
            resultPanel.setGraph(graph);
            outputArea.setText("Generated weighted graph with " + nodeCount + " nodes\n");
            stepButton.setEnabled(true);
            runAllButton.setEnabled(true);
            isRunning = false;
            resetVisualization();
            repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stepAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        String result = null;

        if ("Prim".equals(algorithm)) {
            result = graph.stepPrim();
            if (result == null) {
                mstEdges = graph.getPrimResult();
                resultPanel.setMSTEdges(mstEdges);
                outputArea.append("Prim's algorithm completed. MST weight: " + calculateMSTWeight() + "\n");
            } else {
                outputArea.append(result + "\n");
            }
        } else { // Kruskal
            result = graph.stepKruskal();
            if (result == null) {
                mstEdges = graph.getKruskalResult();
                resultPanel.setMSTEdges(mstEdges);
                outputArea.append("Kruskal's algorithm completed. MST weight: " + calculateMSTWeight() + "\n");
            } else {
                outputArea.append(result + "\n");
            }
        }

        if (result == null) {
            stepButton.setEnabled(false);
            isRunning = false;
        }

        graphPanel.setCurrentStep(graph.getCurrentStep());
        graphPanel.setMSTEdges(mstEdges);
        graphPanel.repaint();
        resultPanel.repaint();
    }

    private void runAlgorithm() {
        if (graph == null) {
            JOptionPane.showMessageDialog(this, "Please generate a graph first", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String algorithm = (String) algorithmComboBox.getSelectedItem();
        long startTime = System.nanoTime();

        if ("Prim".equals(algorithm)) {
            mstEdges = graph.runPrim();
            outputArea.append("Prim's MST edges:\n");
        } else {
            mstEdges = graph.runKruskal();
            outputArea.append("Kruskal's MST edges:\n");
        }

        for (Edge edge : mstEdges) {
            outputArea.append(edge.src + " - " + edge.dest + " : " + edge.weight + "\n");
        }
        outputArea.append("Total MST weight: " + calculateMSTWeight() + "\n");

        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e6;
        outputArea.append("Execution time: " + duration + " ms\n");

        resultPanel.setMSTEdges(mstEdges);
        graphPanel.setCurrentStep(graph.getCurrentStep());
        graphPanel.setMSTEdges(mstEdges);
        graphPanel.repaint();
        resultPanel.repaint();
    }

    private int calculateMSTWeight() {
        int total = 0;
        for (Edge edge : mstEdges) {
            total += edge.weight;
        }
        return total;
    }

    private void resetVisualization() {
        if (graph != null) {
            graph.resetAlgorithmState();
            mstEdges.clear();
            graphPanel.setCurrentStep(0);
            graphPanel.setMSTEdges(mstEdges);
            resultPanel.clearResults();
            outputArea.setText("Graph reset. Ready to run algorithm.\n");
            isRunning = false;
            stepButton.setEnabled(true);
            graphPanel.repaint();
            resultPanel.repaint();
        }
    }
}

class MSTGraphPanel extends JPanel {
    private WeightedGraph graph;
    private List<Edge> mstEdges = new ArrayList<>();
    private int currentStep = 0;
    private Map<Integer, Point> nodePositions = new HashMap<>();
    private static final int NODE_RADIUS = 20;

    public MSTGraphPanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Graph with MST Highlighted"));
    }

    public void setGraph(WeightedGraph graph) {
        this.graph = graph;
        calculateNodePositions();
        repaint();
    }

    public void setCurrentStep(int step) {
        this.currentStep = step;
        repaint();
    }

    public void setMSTEdges(List<Edge> edges) {
        this.mstEdges = edges;
        repaint();
    }

    private void calculateNodePositions() {
        if (graph == null) return;

        int nodeCount = graph.getNodeCount();
        int centerX = getPreferredSize().width / 2;
        int centerY = getPreferredSize().height / 2;
        int radius = Math.min(centerX, centerY) - 50;

        for (int i = 0; i < nodeCount; i++) {
            double angle = 2 * Math.PI * i / nodeCount;
            int x = centerX + (int)(radius * Math.sin(angle));
            int y = centerY - (int)(radius * Math.cos(angle));
            nodePositions.put(i, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null) return;

        // Draw all edges (light gray)
        int[][] adjacencyMatrix = graph.getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    Point p1 = nodePositions.get(i);
                    Point p2 = nodePositions.get(j);

                    // Check if this edge is in MST
                    boolean inMST = false;
                    for (Edge edge : mstEdges) {
                        if ((edge.src == i && edge.dest == j) || (edge.src == j && edge.dest == i)) {
                            inMST = true;
                            break;
                        }
                    }

                    // Draw edge
                    g2d.setColor(inMST ? new Color(50, 205, 50) : new Color(220, 220, 220));
                    g2d.setStroke(inMST ? new BasicStroke(3) : new BasicStroke(1));
                    g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));

                    // Draw weight
                    g2d.setColor(Color.BLACK);
                    int midX = (p1.x + p2.x) / 2;
                    int midY = (p1.y + p2.y) / 2;
                    g2d.drawString(String.valueOf(adjacencyMatrix[i][j]), midX, midY);
                }
            }
        }

        // Draw nodes
        for (int i = 0; i < graph.getNodeCount(); i++) {
            Point p = nodePositions.get(i);

            // Draw node
            g2d.setColor(new Color(70, 130, 180)); // Steel blue
            g2d.fill(new Ellipse2D.Double(p.x - NODE_RADIUS, p.y - NODE_RADIUS,
                    NODE_RADIUS * 2, NODE_RADIUS * 2));

            // Draw node label
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String label = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label, p.x - labelWidth/2, p.y + labelHeight/4);
        }
    }
}

class MSTResultPanel extends JPanel {
    private WeightedGraph graph;
    private List<Edge> mstEdges = new ArrayList<>();

    public MSTResultPanel() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createTitledBorder("Minimum Spanning Tree"));
    }

    public void setGraph(WeightedGraph graph) {
        this.graph = graph;
        repaint();
    }

    public void setMSTEdges(List<Edge> edges) {
        this.mstEdges = edges;
        repaint();
    }

    public void clearResults() {
        this.mstEdges.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (graph == null) return;

        if (mstEdges.isEmpty()) {
            g2d.setColor(Color.BLACK);
            g2d.drawString("Run an algorithm to see MST", 50, 50);
            return;
        }

        // Draw MST title and total weight
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Minimum Spanning Tree", 20, 30);

        int totalWeight = 0;
        for (Edge edge : mstEdges) {
            totalWeight += edge.weight;
        }
        g2d.drawString("Total Weight: " + totalWeight, 20, 60);

        // Draw MST edges
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        int y = 90;
        for (Edge edge : mstEdges) {
            g2d.drawString(edge.src + " - " + edge.dest + " : " + edge.weight, 30, y);
            y += 25;
        }

        // Draw MST visualization if space allows
        if (getHeight() > 300) {
            int visY = y + 20;
            int visHeight = getHeight() - visY - 20;

            if (visHeight > 100) {
                drawMSTVisualization(g2d, 50, visY, Math.min(300, getWidth() - 100), visHeight);
            }
        }
    }

    private void drawMSTVisualization(Graphics2D g2d, int x, int y, int width, int height) {
        if (graph == null || mstEdges.isEmpty()) return;

        // Calculate node positions for MST visualization
        Map<Integer, Point> nodePositions = new HashMap<>();
        int nodeCount = graph.getNodeCount();
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        int radius = Math.min(width, height) / 2 - 20;

        for (int i = 0; i < nodeCount; i++) {
            double angle = 2 * Math.PI * i / nodeCount;
            int px = centerX + (int)(radius * Math.sin(angle));
            int py = centerY - (int)(radius * Math.cos(angle));
            nodePositions.put(i, new Point(px, py));
        }

        // Draw MST edges
        g2d.setColor(new Color(50, 205, 50));
        g2d.setStroke(new BasicStroke(2));

        for (Edge edge : mstEdges) {
            Point p1 = nodePositions.get(edge.src);
            Point p2 = nodePositions.get(edge.dest);
            g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
        }

        // Draw nodes
        int nodeRadius = Math.min(15, radius / 5);
        for (int i = 0; i < nodeCount; i++) {
            Point p = nodePositions.get(i);

            // Draw node
            g2d.setColor(new Color(70, 130, 180));
            g2d.fill(new Ellipse2D.Double(p.x - nodeRadius, p.y - nodeRadius,
                    nodeRadius * 2, nodeRadius * 2));

            // Draw node label
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String label = String.valueOf(i);
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            int labelHeight = fm.getAscent();
            g2d.drawString(label, p.x - labelWidth/2, p.y + labelHeight/4);
        }
    }
}

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}