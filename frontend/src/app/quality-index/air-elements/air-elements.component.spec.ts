import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirElementsComponent } from './air-elements.component';

describe('AirElementsComponent', () => {
  let component: AirElementsComponent;
  let fixture: ComponentFixture<AirElementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AirElementsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AirElementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
