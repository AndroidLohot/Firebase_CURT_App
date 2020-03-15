package com.example.a;

import java.io.Serializable;

public class DeliNote implements Serializable
{
  private String userIdl;
  private String name;
  private float kiBile;
  private float drBile;
  private float paBile;

  public DeliNote() {
  }

  public DeliNote(String userIdl, String name, float kiBile, float drBile, float paBile) {
    this.userIdl = userIdl;
    this.name = name;
    this.kiBile = kiBile;
    this.drBile = drBile;
    this.paBile = paBile;
  }

  public DeliNote(String name, float kiBile, float drBile, float paBile) {
    this.name = name;
    this.kiBile = kiBile;
    this.drBile = drBile;
    this.paBile = paBile;
  }

  public String getUserIdl() {
    return userIdl;
  }

  public void setUserIdl(String userIdl) {
    this.userIdl = userIdl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getKiBile() {
    return kiBile;
  }

  public void setKiBile(float kiBile) {
    this.kiBile = kiBile;
  }

  public float getDrBile() {
    return drBile;
  }

  public void setDrBile(float drBile) {
    this.drBile = drBile;
  }

  public float getPaBile() {
    return paBile;
  }

  public void setPaBile(float paBile) {
    this.paBile = paBile;
  }

  @Override
  public String toString() {
    return name;
  }
}
