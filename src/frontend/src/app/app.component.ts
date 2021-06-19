import { Component, OnInit } from '@angular/core';
import { Building } from './building';
import { BuildingService } from './building.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  public buildings: Building[];
  public editBuilding: Building;
  public deleteBuilding: Building;

  constructor(private buildingService: BuildingService) {}

  ngOnInit() {
    this.getBuildings();
  }

  public getBuildings(): void {
    this.buildingService.getBuilding().subscribe(
      (response: Building[]) => {
        this.buildings = response;
        console.log(this.buildings);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddBuilding(addForm: NgForm): void {
    document.getElementById('add-building-form').click();
    this.buildingService.addBuilding(addForm.value).subscribe(
      (response: Building) => {
        console.log(response);
        this.getBuildings();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateEmloyee(building: Building): void {
    this.buildingService.updateBuilding(building).subscribe(
      (response: Building) => {
        console.log(response);
        this.getBuildings();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteBuilding(buildingId: number): void {
    this.buildingService.deleteBuilding(buildingId).subscribe(
      (response: void) => {
        console.log(response);
        this.getBuildings();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchBuildings(key: string): void {
    console.log(key);
    const results: Building[] = [];
    for (const building of this.buildings) {
      if (
        building.buildingNumber.toLowerCase().indexOf(key.toLowerCase()) !==
        -1 ||
        building.description.toLowerCase().indexOf(key.toLowerCase()) !== -1
      ) {
        results.push(building);
      }
    }
    this.buildings = results;
    if (results.length === 0 || !key) {
      this.getBuildings();
    }
  }

  public onOpenModal(building: Building, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addBuildingModal');
    }
    if (mode === 'edit') {
      this.editBuilding = building;
      button.setAttribute('data-target', '#updateBuildingModal');
    }
    if (mode === 'delete') {
      this.deleteBuilding = building;
      button.setAttribute('data-target', '#deleteBuildingModal');
    }
    container.appendChild(button);
    button.click();
  }
}
