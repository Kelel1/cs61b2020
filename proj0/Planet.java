public class Planet {
  
  public double xxPos;
  public double yyPos;

  public double xxVel;
  public double yyVel;

  public double mass;

  public String imgFileName;

  final double GRAVITY = 6.67e-11;

  public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {

                this.xxPos       = xP;
                this.yyPos       = yP;

                this.xxVel       = xV;
                this.yyVel       = yV;

                this.mass        = m;

                this.imgFileName = img;
              }

  
  public Planet(Planet p) {

    this.xxPos       = p.xxPos;
    this.yyPos       = p.yyPos;

    this.xxVel       = p.xxVel;
    this.yyVel       = p.yyVel;

    this.mass        = p.mass;

    this.imgFileName = p.imgFileName;
  }

  public double calcDistance(Planet p) {

    return Math.sqrt( ((p.xxPos - this.xxPos) * (p.xxPos - this.xxPos)) +
                      ((p.yyPos - this.yyPos) * (p.yyPos - this.yyPos))) ;
  }


  public double calcForceExertedBy(Planet p) {

    double radius = calcDistance(p);

    return (((GRAVITY * this.mass * p.mass) / (radius * radius)));
  }

  public double calcForceExertedByX(Planet p) {

    double netForce = calcForceExertedBy(p);
    double radius   = calcDistance(p);

    double dx       = p.xxPos - this.xxPos;
    
   

    return ((netForce * dx) / radius);

  }

  public double calcForceExertedByY(Planet p) {

    double netForce = calcForceExertedBy(p);
    double radius   = calcDistance(p);

    double dy       = p.yyPos - this.yyPos;
    
   

    return ((netForce * dy) / radius);
    
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    
    double netForce = 0.0;

    for (Planet p : planets) {
      if (!this.equals(p)) {
        netForce += calcForceExertedByX(p);
      }
    }
    return netForce;
  }   
  public double calcNetForceExertedByY(Planet[] planets) {

    double netForce = 0.0;

    for (Planet p : planets) {
      if (!this.equals(p)) {
        netForce += calcForceExertedByY(p);
      }
    }
    return netForce;
  }

  public void update(double dt, double fX, double fY) {

    double accelNetX    = fX / this.mass;
    double accelNetY    = fY / this.mass;

    double newVelocityX = this.xxVel + dt * accelNetX;
    double newVelocityY = this.yyVel + dt * accelNetY;

    double newPositionX = this.xxPos + dt * newVelocityX;
    double newPositionY = this.yyPos + dt * newVelocityY;

    this.xxPos          = newPositionX;
    this.yyPos          = newPositionY;

    this.xxVel          = newVelocityX;
    this.yyVel          = newVelocityY;

  }

  public void draw() {
    StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
  }

}