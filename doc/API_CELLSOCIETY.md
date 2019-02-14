# Cell Society API Critique

### Group 8 API Review for Cell Society 

    package Configuration;
    public class SimulationInfo { 
        public SimulationInfo(Map<String, String> values) 
        public String getTitle()
        public SimulationType getType()
        public SimulationType getSimType() 
        public Grid getGridType() 
        public Neighborhood getNeighborhood()
        public Edge getEdge()
        public int getCellSize()
        public Paint[] getStateColors()
        public boolean getOutline()
        public double[] getParameters()
        public int getWidth()
        public int getHeight()
        public Shape getShape()
        public int[][] getIntegerConfiguration()
        public List<String> getSimStrings()
    }
    
In looking at this code, here are methods that we think should be in the public API: 

        public SimulationInfo(Map<String, String> values) 
        public String getTitle()
        public SimulationType getType()
        public Grid getGridType() 
        public Neighborhood getNeighborhood()
        public Edge getEdge()
        public int getCellSize()
        public Paint[] getStateColors()
        public boolean getOutline()
        public double[] getParameters()
        public int getWidth()
        public int getHeight()
        public Shape getShape()
        public int[][] getIntegerConfiguration()
        public List<String> getSimStrings()
        
There are essentially no methods here that we believe should be constrained to a private API because the return values of these methods are required to be used by the frontend/other parts of the program and should be accessible to those looking to interface with the program. However, there was an error as the simulation type getter was named two different things at different times throughout the process, so 

    public SimulationType getSimType() 
    
should have been deleted.