public class NBody {
  

  public static double readRadius(String file) {

    In in = new In(file);
    int firstItem = in.readInt();

    double radius = in.readDouble();

    return radius;

  }

  public static Planet[] readPlanets(String file) {

    Planet[] planetArray = new Planet[5];

    In in = new In(file);

    int totlPlanets = in.readInt();
    Planet[] bodies = new Planet[totlPlanets];
    double radius = in.readDouble(); 
    
    
   for (int i = 0; i < bodies.length; i++ ) {


    double xxPos      = in.readDouble();
    double yyPos      = in.readDouble();
    double xxVel      = in.readDouble();
    double yyVel      = in.readDouble();
    double mass       = in.readDouble();
    String planetName = in.readString();
   
    Planet body = new Planet(xxPos, yyPos, xxVel,
                           yyVel, mass, planetName);  

    bodies[i] = body;

  } 
   return bodies;
}

public static void main(String[] args) {
    
  double        T = Double.parseDouble(args[0]);  
  double       dt = Double.parseDouble(args[1]);
  String filename = args[2];

  NBody.readPlanets(filename);
  NBody.readRadius(filename);

  Planet[] planets = NBody.readPlanets(filename);
   
  StdDraw.setScale(-NBody.readRadius(filename), NBody.readRadius(filename));
    
  StdDraw.picture(0, 0, "images/starfield.jpg");

  for(int i = 0; i < planets.length; i++) {
      planets[i].draw();
  }

  StdDraw.enableDoubleBuffering();

  for(double time = 0.0; time <= T; time += dt) {
        
    double[] xForces = new double[planets.length];
    double[] yForces = new double[planets.length];

    for(int j = 0; j < planets.length; j++) {

        xForces[j] = planets[j].calcNetForceExertedByX(planets);
        yForces[j] = planets[j].calcNetForceExertedByY(planets);
    }

    for(int k = 0; k < planets.length; k++) {

        planets[k].update(dt, xForces[k], yForces[k]);
    }
        
    StdDraw.picture(0, 0, "images/starfield.jpg");

    for(int m = 0; m < planets.length; m++) {
        planets[m].draw();
    }

    StdDraw.show(10);
                
    }


}
}