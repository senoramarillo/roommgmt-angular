import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Building } from './building';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class BuildingService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public getBuilding(): Observable<Building[]> {
    return this.http.get<Building[]>(`${this.apiServerUrl}/buildings`);
  }

  public addBuilding(building: Building): Observable<Building> {
    return this.http.post<Building>(`${this.apiServerUrl}/buildings`, building);
  }

  public updateBuilding(building: Building): Observable<Building> {
    return this.http.put<Building>(`${this.apiServerUrl}/buildings`, building);
  }

  public deleteBuilding(buildingId: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/buildings/${buildingId}`
    );
  }
}
